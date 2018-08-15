package com.app.exceptions;

public class UnsupportedCommandException extends RuntimeException {

    public UnsupportedCommandException(String command) {
        super(String.format("%s command is unsupported.", command));
    }

    public UnsupportedCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}

