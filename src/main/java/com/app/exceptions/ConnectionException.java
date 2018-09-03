package com.app.exceptions;

public class ConnectionException extends RuntimeException {

    public ConnectionException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
