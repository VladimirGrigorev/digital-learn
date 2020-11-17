package com.netcracker.project.entity.dto.response;

import lombok.Data;

/**
 * Auth response DTO
 */
@Data
public class AuthResponse {
    /**
     * AuthResponse access token
     */
    private String accessToken;

    /**
     * AuthResponse token type
     */
    private String tokenType = "Bearer";

    /**
     * Constructor
     */
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
