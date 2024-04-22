package org.example.controller;

import org.example.dto.User;

public class Session {
    public User loginedUser;

    public User getLoginedUser() {
        return loginedUser;
    }

    public void setLoginedUser(User loginedUser) {
        this.loginedUser = loginedUser;
    }
    public boolean isLogined() {
        return loginedUser != null;
    }
}
