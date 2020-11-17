package com.netcracker.project.entity.dto.response;

import lombok.Data;

/**
 * Jwt authentication response DTO
 */
@Data
public class JwtAuthenticationResponse {

    /**
     * JwtAuthenticationResponse access token
     */
    private String accessToken;

    /**
     * JwtAuthenticationResponse refresh token
     */
    private String refreshToken;

    /**
     * JwtAuthenticationResponse token type
     */
    private String tokenType = "Bearer";

    /**
     * JwtAuthenticationResponse expires in msec
     */
    private Long expiresInMsec;

    /**
     * Constructor
     */
    public JwtAuthenticationResponse(String accessToken, String refreshToken, Long expiresInMsec) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresInMsec = expiresInMsec;
    }
}
