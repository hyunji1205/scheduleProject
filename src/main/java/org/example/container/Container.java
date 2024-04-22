package org.example.container;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.Session;
import org.example.dao.ScheduleDao;
import org.example.dao.UserDao;
import org.example.db.DBConnection;
import org.example.service.ExportService;
import org.example.service.ScheduleService;
import org.example.service.UserService;

import java.util.Scanner;

@Getter
@Setter

public class Container {
    public static Scanner sc;

    public static Session session;
    public static DBConnection dbConnection;
    public static ScheduleDao scheduleDao;
    public static UserDao userDao;

    public static ScheduleService scheduleService;
    public static UserService userService;
    public static ExportService exportService;

    static {
        scheduleDao = new ScheduleDao();
        userDao = new UserDao();
        scheduleService = new ScheduleService();
        userService = new UserService();
        exportService = new ExportService();
    }

    public static Scanner getSc() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }

        return sc;
    }

    public static Session getSession() {
        if (session == null) {
            session = new Session();
        }

        return session;
    }

    public static DBConnection getDBConnection() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }


}