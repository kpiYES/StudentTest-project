package com.app.exceptions;

public class InteractionDBException extends RuntimeException {

    public InteractionDBException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

    public InteractionDBException(final String s) {
        super(s);
    }

}
