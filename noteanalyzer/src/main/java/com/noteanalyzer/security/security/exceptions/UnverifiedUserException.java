package com.noteanalyzer.security.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;


public class UnverifiedUserException extends AuthenticationServiceException {
    private static final long serialVersionUID = 3705043083010304496L;

    public UnverifiedUserException(String msg) {
        super(msg);
    }
}
