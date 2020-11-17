package com.netcracker.project.entity.dto.reguest;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Sign up request DTO
 */
@Data
public class SignUpRequest {

    /**
     * SignUpRequest user name
     */
    @NotBlank
    private String username;

    /**
     * SignUpRequest email
     */
    @NotBlank
    @Email
    private String email;

    /**
     * SignUpRequest password
     */
    @NotBlank
    private String password;
}
