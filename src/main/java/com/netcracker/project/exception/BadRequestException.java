package com.netcracker.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for bad request
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * Constructor
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructor
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
