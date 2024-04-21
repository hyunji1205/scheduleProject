package org.example.controller;

import org.example.dto.User;

public abstract class Controller {
    public static User loginedUser;

    public static boolean isLogined() {
        return loginedUser != null;
    }
    public abstract void doAction(String cmd, String actionMethodName);
    public abstract void makeTestData();

}
