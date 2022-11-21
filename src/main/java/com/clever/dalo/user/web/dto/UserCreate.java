package com.clever.dalo.user.web.dto;

import lombok.Data;

public class UserCreate {

    private final String login;

    public UserCreate(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
}
