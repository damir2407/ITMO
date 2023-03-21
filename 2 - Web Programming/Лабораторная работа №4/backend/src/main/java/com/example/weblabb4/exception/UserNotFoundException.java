package com.example.weblabb4.exception;

import java.util.NoSuchElementException;


public class UserNotFoundException extends NoSuchElementException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
