package org.example.dao;

import org.example.dto.User;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> users;

    public UserDao() {
        users = new ArrayList<>();
    }
    public void join(User user) {
        users.add(user);
    }
    public int getUserIndexByLoginName(String loginName) {
        int i = 0;

        for ( User user : users ) {
            if ( user.loginName.equals(loginName) ) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public User getUserByLoginName(String loginName) {
        int index = getUserIndexByLoginName(loginName);

        if ( index == -1 ) {
            return null;
        }

        return users.get(index);
    }

}

