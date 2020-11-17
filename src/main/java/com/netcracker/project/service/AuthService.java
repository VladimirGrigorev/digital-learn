package com.netcracker.project.service;

import com.netcracker.project.entity.User;
import com.netcracker.project.entity.dto.reguest.LoginRequest;
import com.netcracker.project.entity.dto.reguest.RefreshTokenRequest;
import com.netcracker.project.entity.dto.reguest.SignUpRequest;
import com.netcracker.project.entity.dto.response.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    /**
     * Sign up user
     * @param signUpRequest - request for auth
     * @return user
     */
    User signUp(SignUpRequest signUpRequest);

    /**
     * Log in user
     * @param loginRequest - request for login
     * @return jwt authentication response
     */
    JwtAuthenticationResponse logIn(LoginRequest loginRequest);

    /**
     * Refresh access token
     * @param refreshTokenRequest - request for refresh token
     * @return jwt authentication response
     */
    ResponseEntity<JwtAuthenticationResponse> refreshAccessToken(RefreshTokenRequest refreshTokenRequest);
}
