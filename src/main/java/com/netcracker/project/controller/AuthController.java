package com.netcracker.project.controller;

import com.netcracker.project.entity.dto.reguest.RefreshTokenRequest;
import com.netcracker.project.entity.dto.response.ApiResponse;
import com.netcracker.project.entity.dto.reguest.LoginRequest;
import com.netcracker.project.entity.dto.reguest.SignUpRequest;
import com.netcracker.project.service.AuthService;
import com.netcracker.project.service.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Controller for Authenticate user
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Authenticate service
     */
    private final AuthService authService;

    /**
     * Constructor
     */
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    /**
     * Authenticate user
     * @param loginRequest - login request
     * @return jwt auth response
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.logIn(loginRequest));
    }

    /**
     * Refresh access token user
     * @param refreshTokenRequest - refresh token request
     * @return jwt auth response
     */
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshAccessToken(refreshTokenRequest);
    }

    /**
     * Register user
     * @param signUpRequest - sign up request
     * @return - message about successful registration
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/me")
                .buildAndExpand(authService.signUp(signUpRequest).getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully"));
    }
}