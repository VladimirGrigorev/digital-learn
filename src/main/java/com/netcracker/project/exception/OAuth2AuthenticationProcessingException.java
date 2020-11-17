package com.netcracker.project.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception for OAuth2 authentication processing
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    /**
     * Constructor
     */
    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructor
     */
    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}
