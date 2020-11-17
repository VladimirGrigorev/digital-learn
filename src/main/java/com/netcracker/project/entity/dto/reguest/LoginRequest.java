package com.netcracker.project.entity.dto.reguest;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Login request DTO
 */
@Data
public class LoginRequest {

    /**
     * LoginRequest email
     */
    @NotBlank
    @Email
    private String email;

    /**
     * LoginRequest password
     */
    @NotBlank
    private String password;
}
