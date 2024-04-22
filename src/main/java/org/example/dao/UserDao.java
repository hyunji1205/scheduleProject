package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    public List<User> users;
    private DBConnection dbConnection;

    public UserDao() {
        users = new ArrayList<>();
        dbConnection = Container.getDBConnection();
    }
    public int join(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO users "));
        sb.append(String.format("loginName = '%s' ", user.loginName));
        sb.append(String.format("loginPw = '%s' ", user.loginPw));

        return dbConnection.insert(sb.toString());
    }

    public User getUserByLoginName(String loginName) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM users "));
        sb.append(String.format("WHERE loginName = '%s' ", loginName));

        Map<String, Object> userRow = dbConnection.selectRow((sb.toString()));

        return new User(userRow);
    }
}

