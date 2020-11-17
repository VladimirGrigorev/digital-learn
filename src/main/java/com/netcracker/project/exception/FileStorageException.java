package com.netcracker.project.exception;

/**
 * Exception for file storage
 */
public class FileStorageException extends RuntimeException {

    /**
     * Constructor
     */
    public FileStorageException(String message) {
        super(message);
    }

    /**
     * Constructor
     */
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
