package com.clever.dalo.user.web.dto;

public class UserUpdate {

    private final String login;

    public UserUpdate(String login) {

        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
