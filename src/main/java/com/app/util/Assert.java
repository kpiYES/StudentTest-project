package com.app.util;

import javax.persistence.EntityExistsException;

public class Assert {
    public static void isNotNull(Object object, String message) {

        if (object == null) {
            throw new EntityExistsException(message);
        }
    }
}
