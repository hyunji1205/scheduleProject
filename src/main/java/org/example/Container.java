package org.example;


import org.example.dao.ScheduleDao;
import org.example.dao.UserDao;
import org.example.service.ScheduleService;

public class Container {
    public static ScheduleDao scheduleDao;
    public static UserDao userDao;

    public static ScheduleService scheduleService;

    static {
        scheduleDao = new ScheduleDao();
        userDao = new UserDao();

        scheduleService = new ScheduleService();
    }
}