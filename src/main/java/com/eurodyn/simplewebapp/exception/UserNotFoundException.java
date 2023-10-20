package com.eurodyn.simplewebapp.exception;

public class UserNotFoundException extends Exception {

    private final Long id;

    public static UserNotFoundException createWith(Long id) {
        return new UserNotFoundException(id);
    }

    private UserNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "User with id '" + id + "' not found.";
    }

}
