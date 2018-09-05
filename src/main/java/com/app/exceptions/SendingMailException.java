package com.app.exceptions;

public class SendingMailException extends RuntimeException {

    public SendingMailException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
