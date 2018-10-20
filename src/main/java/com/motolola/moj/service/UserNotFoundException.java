package com.motolola.moj.service;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(int id) {
        super("Message: Could not find user " + id);
    }
}
