package com.app.exceptions;

public class PasswordValidationException extends RuntimeException {

    public PasswordValidationException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
