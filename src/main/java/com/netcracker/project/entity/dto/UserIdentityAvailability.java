package com.netcracker.project.entity.dto;

import lombok.Data;

/**
 * User identity availability DTO
 */
@Data
public class UserIdentityAvailability {
    /**
     * UserIdentityAvailability available
     */
    private Boolean available;

    /**
     * Constructor
     */
    public UserIdentityAvailability(Boolean available) {
        this.available = available;
    }
}
