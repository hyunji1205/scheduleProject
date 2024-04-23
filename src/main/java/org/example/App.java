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


        // 사용자 입력에 따라 명령어 분류
        while (true) {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            HashMap<String, ArrayList<String>> listMap = new HashMap<String, ArrayList<String>>();

            // 오늘 날짜 가져오기
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 오늘 날짜
            String[] toDate = today.split("-"); // 오늘 날짜를 년, 월, 일로 나눔
            calender(listMap, Integer.parseInt(toDate[0]), Integer.parseInt(toDate[1]));
            // 오늘 날짜가 포함 된 월의 달력 자동 출력


            //메뉴 옵션 출력
            System.out.println(" ----------------------------------");
            System.out.println("1. 로그인");
            System.out.println("2. 일정등록");
            System.out.println("3. 일정변경");
            System.out.println("4. 일정검색");
            System.out.println("5. 일정목록");
            System.out.println("6. 로그아웃");
            System.out.println(" ----------------------------------");

            System.out.print("메뉴를 선택하세요: ");
            String cmd = sc.nextLine().trim();

            Controller controller = null;


            if (cmd.equals("exit")) {
                break;
            }


            // 명령어에 따른 컨트롤러 선택
            if (cmd.contains("회원") || cmd.contains("로그")) {
                controller = userController;
            } else if (cmd.contains("일정")) {
                controller = scheduleController;
            } else {
                System.out.printf("%s(은)는 잘못된 입력입니다. 다시 입력해주세요.\n", cmd);
                continue;
            }


            // 권한 확인
            if (cmd.equals("회원가입") || cmd.equals("로그인")) {
                if (Container.getSession().isLogined()) {
                    System.out.println("로그아웃 후 이용해주세요.");
                    continue;
                }
            } else if (cmd.equals("일정등록") || cmd.equals("일정변경") || cmd.equals("일정검색") || cmd.equals("일정목록")) {
                if (Container.getSession().isLogined() == false) {
                    System.out.println("로그인 후 이용해주세요.");
                    continue;
                }
            }

            // 해당 컨트롤러에 명령어 전달
            if (controller != null) {
                controller.doAction(cmd);
            }
        }

        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
}








