package org.example;

import org.example.container.Container;
import org.example.controller.Controller;
import org.example.controller.ExportController;
import org.example.controller.ScheduleController;
import org.example.controller.UserController;
import org.example.db.DBConnection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.example.Calender.calender;

public class App {
    public App() {
        DBConnection.DB_NAME = "scheduleProject";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();

    }

    public void start() {

        System.out.println("== 프로그램 시작 ==");


        Scanner sc = new Scanner(System.in);

        UserController userController = new UserController(sc);
        ScheduleController scheduleController = new ScheduleController(sc);
        ExportController exportController = new ExportController(sc);

        // 오늘 날짜 가져오기
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // 달력 출력
        HashMap<String, ArrayList<String>> listMap = new HashMap<>();
        calender(listMap, today.getYear(), today.getMonthValue());

        System.out.println(" ----------------------------------");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 일정등록");
        System.out.println("4. 일정변경");
        System.out.println("5. 일정검색");
        System.out.println("6. 일정목록");
        System.out.println("7. 로그아웃");
        System.out.println(" ----------------------------------");


        // 사용자 입력에 따라 명령어 분류
        while (true) {
            System.out.print("메뉴를 선택하세요: ");
            String cmd = sc.nextLine();
            cmd = cmd.trim();

            Controller controller = null;


            if (cmd.length() == 0) {
                continue;
            }

            if (cmd.equals("exit")) {
                break;
            }


            if (cmd.indexOf("회원") != -1 || cmd.indexOf("로그") != -1) {
                controller = userController;
            } else if (cmd.indexOf("일정") != -1) {
                controller = scheduleController;
            } else {
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", cmd);
                continue;
            }


            switch (cmd) {
                case "회원가입":
                case "로그인":
                    if (Container.getSession().isLogined()) {
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
                case "일정등록":
                case "일정변경":
                case "일정검색":
                case "일정목록":
                    if (Container.getSession().isLogined() == false) {
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;

            }
            controller.doAction(cmd);
        }

        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
}








