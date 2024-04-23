package org.example.service;

import org.example.container.Container;
import org.example.dao.UserDao;
import org.example.dto.User;

public class UserService {
    private UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = Container.getUserDao();
    }

    public int join(String loginName, String loginPw) {
        User user = new User(loginName, loginPw);
        return userDao.join(user);
    }

    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }
}
//    public int getUserIndexByLoginName(String loginName) {
//        return userDao.getUserIndexByLoginName(loginName);
//    }
//}
