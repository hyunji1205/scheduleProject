package org.example.dao;

import org.example.dto.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> users;

    public UserDao() {
        users = new ArrayList<>();
    }
}
