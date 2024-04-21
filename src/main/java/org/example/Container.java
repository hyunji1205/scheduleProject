package org.example;


import org.example.dao.ScheduleDao;
import org.example.dao.UserDao;
import org.example.service.ScheduleService;
import org.example.service.UserService;

public class Container {
    public static ScheduleDao scheduleDao;
    public static UserDao userDao;

    public static ScheduleService scheduleService;
    public static UserService userService;

    static {
        scheduleDao = new ScheduleDao();
        userDao = new UserDao();
        scheduleService = new ScheduleService();
        userService = new UserService();
    }
}