package org.example;

import org.example.controller.Controller;
import org.example.controller.ScheduleController;
import org.example.controller.UserController;

import java.util.Scanner;

public class App {

    public void start() {

        System.out.println("== 프로그램 시작 ==");


        Scanner sc = new Scanner(System.in);

        UserController userController = new UserController(sc);
        ScheduleController scheduleController = new ScheduleController(sc);

        scheduleController.makeTestData();
        userController.makeTestData();


        while (true) {
            System.out.printf("명령어) ");
            String cmd = sc.nextLine();
            cmd = cmd.trim();

            if (cmd.length() == 0) {
                continue;
            }

            if (cmd.equals("exit")) {
                break;
            }

            String[] cmdBits = cmd.split(" "); // article write / member join
            String controllerName = cmdBits[0]; // article / member
            String actionMethodName = cmdBits[1];

            Controller controller = null;

            if (controllerName.equals("일정")) {
                controller = scheduleController;
            } else if (controllerName.equals("회원")) {
                controller = userController;
            } else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }

            String actionName = controllerName + "/" + actionMethodName;

            switch ( actionName ) {
                case "일정/등록":
                case "일정/변경":
                case "일정/검색":
                case "일정/목록":
                case "회원/로그아웃":
                    if ( Controller.isLogined() == false ) {
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            switch ( actionName ) {
                case "회원/로그인":
                case "회원/가입":
                    if ( Controller.isLogined() ) {
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);
        }


            sc.close();
            System.out.println("== 프로그램 끝 ==");
        }
    }






