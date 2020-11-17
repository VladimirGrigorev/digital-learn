package com.netcracker.project.service.impl;

import com.netcracker.project.config.AppProperties;
import com.netcracker.project.entity.Cart;
import com.netcracker.project.entity.JwtRefreshToken;
import com.netcracker.project.entity.User;
import com.netcracker.project.entity.dto.reguest.LoginRequest;
import com.netcracker.project.entity.dto.reguest.RefreshTokenRequest;
import com.netcracker.project.entity.dto.reguest.SignUpRequest;
import com.netcracker.project.entity.dto.response.JwtAuthenticationResponse;
import com.netcracker.project.entity.enums.AuthProvider;
import com.netcracker.project.exception.BadRequestException;
import com.netcracker.project.repository.JwtRefreshTokenRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.security.TokenProvider;
import com.netcracker.project.security.UserPrincipal;
import com.netcracker.project.service.AuthService;
import com.netcracker.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service(value = "authService")
@Transactional
public class AuthServiceImpl implements AuthService {

    /**
     * Beans for Auth
     */
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtRefreshTokenRepository jwtRefreshTokenRepository;
    private final AppProperties appProperties;
    private final UserService userService;

    /**
     * Constructor
     */
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           TokenProvider tokenProvider,
                           JwtRefreshTokenRepository jwtRefreshTokenRepository,
                           AppProperties appProperties,
                           UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.jwtRefreshTokenRepository = jwtRefreshTokenRepository;
        this.appProperties = appProperties;
        this.userService = userService;
    }

    /**
     * Sign up user
     * @param signUpRequest - request for auth
     * @return user
     */
    public User signUp(SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Cart cart = new Cart(user);
        user.setCart(cart);

        User result = userService.register(user);

        log.info("user signed up");

        return result;
    }

    /**
     * Log in user
     * @param loginRequest - request for login
     * @return jwt authentication response
     */
    public JwtAuthenticationResponse logIn(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String accessToken = tokenProvider.generateToken(userPrincipal);
        String refreshToken = tokenProvider.generateRefreshToken();

        saveRefreshToken(userPrincipal, refreshToken);

        log.info("user logged in");

        return new JwtAuthenticationResponse(accessToken, refreshToken,
                appProperties.getAuth().getTokenExpirationMsec());
    }

    /**
     * Refresh access token
     * @param refreshTokenRequest - request for refresh token
     * @return jwt authentication response
     */
    public ResponseEntity<JwtAuthenticationResponse> refreshAccessToken(RefreshTokenRequest refreshTokenRequest){
        return jwtRefreshTokenRepository.findById(refreshTokenRequest.getRefreshToken()).map(jwtRefreshToken -> {
            User user = jwtRefreshToken.getUser();
            String accessToken = tokenProvider.generateToken(UserPrincipal.create(user));
            return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, jwtRefreshToken.getToken(),
                    appProperties.getAuth().getTokenExpirationMsec()));
        }).orElseThrow(() -> new BadRequestException("Invalid Refresh Token"));
    }

    /**
     * Save refresh token
     * @param userPrincipal - user data
     * @param refreshToken - refresh token
     */
    private void saveRefreshToken(UserPrincipal userPrincipal, String refreshToken) {

        JwtRefreshToken jwtRefreshToken = new JwtRefreshToken(refreshToken);
        jwtRefreshToken.setUser(userRepository.getOne(userPrincipal.getId()));

        Instant expirationDateTime = Instant.now().plus(appProperties.getAuth().getAliveTime(), ChronoUnit.DAYS);
        jwtRefreshToken.setExpirationDateTime(expirationDateTime);

        jwtRefreshTokenRepository.save(jwtRefreshToken);
    }
}
