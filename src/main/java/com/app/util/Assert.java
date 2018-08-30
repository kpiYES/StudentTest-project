package com.app.util;

import javax.persistence.EntityNotFoundException;

public class Assert {

    public static void isNotNull(Object object, String message) {
        if (object == null) {
            throw new EntityNotFoundException(message);
        }
    }
}
