package com.netcracker.project.entity.dto.response;

import lombok.Data;

/**
 * Api response DTO
 */
@Data
public class ApiResponse {
    /**
     * ApiResponse success
     */
    private boolean success;

    /**
     * ApiResponse message
     */
    private String message;

    /**
     * Constructor
     */
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Constructor
     */
    public boolean isSuccess() {
        return success;
    }
}
