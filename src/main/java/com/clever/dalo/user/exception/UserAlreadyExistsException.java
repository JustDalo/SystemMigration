package com.clever.dalo.user.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private final String login;

    public UserAlreadyExistsException(String login) {

        super();
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
