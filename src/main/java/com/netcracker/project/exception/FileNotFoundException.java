package com.netcracker.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for file not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
    /**
     * Constructor
     */
    public FileNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor
     */
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
