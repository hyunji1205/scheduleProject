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
    private static Scanner sc;

    private static Session session;
    private static DBConnection dbConnection;
    private static ScheduleDao scheduleDao;
    private static UserDao userDao;

    private static ScheduleService scheduleService;
    private static UserService userService;
    private static ExportService exportService;

    // 정적 초기화 블록 제거: 객체를 지연 초기화로 변경
    public static synchronized Scanner getSc() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }

    public static synchronized Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public static synchronized DBConnection getDBConnection() {
        if (dbConnection == null) {
            dbConnection = new DBConnection(); // 지연 초기화
        }
        return dbConnection;
    }

    public static synchronized ScheduleDao getScheduleDao() {
        if (scheduleDao == null) {
            scheduleDao = new ScheduleDao(getDBConnection());
        }
        return scheduleDao;
    }

    public static synchronized UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao(getDBConnection());
        }
        return userDao;
    }

    public static synchronized ScheduleService getScheduleService() {
        if (scheduleService == null) {
            scheduleService = new ScheduleService(getScheduleDao());
        }
        return scheduleService;
    }

    public static synchronized UserService getUserService() {
        if (userService == null) {
            userService = new UserService(getUserDao());
        }
        return userService;
    }

    public static synchronized ExportService getExportService() {
        if (exportService == null) {
            exportService = new ExportService(); // 의존성 주입 필요할 경우 설정
        }
        return exportService;
    }
}
