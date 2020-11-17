package com.netcracker.project.entity.dto.reguest;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * RefreshT token request DTO
 */
@Data
public class RefreshTokenRequest {

    /**
     * RefreshTokenRequest refresh token
     */
    @NotBlank
    private String refreshToken;
}
