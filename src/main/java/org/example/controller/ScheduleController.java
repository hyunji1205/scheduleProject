package org.example.controller;

import org.example.dto.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScheduleController extends Controller {
    private Scanner sc;
    private List<Schedule> schedules;
    private String cmd;
    private String actionMethodName;

    public ScheduleController(Scanner sc) {
        this.sc = sc;
        schedules = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;
        this.actionMethodName = actionMethodName;

        switch (actionMethodName) {
            case "등록":
                doWrite();
                break;
            case "변경":
                doChange();
                break;
            case "검색" :
                System.out.println(" == 일정 검색 == ");
                System.out.println(" 1. 날짜로 검색 ");
                System.out.println(" 2. 키워드로 검색 ");
                String dateselect = sc.nextLine();

                if("날짜".equals(dateselect)){
                    searchDate();
                    break;
                }
                else if("키워드".equals(dateselect)){
                    searchKeyword();
                    break;
                }

            case "목록":
                showList();
                break;

            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    public void makeTestData() {
        System.out.println("테스트를 위한 게시물 데이터를 생성합니다");

        schedules.add(new Schedule("2024-04-13", "재민생일"));
        schedules.add(new Schedule("2024-04-20", "오늘"));
        schedules.add(new Schedule("2024-04-23", "재민돼지"));
    }

    public void doWrite() {
        System.out.printf("일정 날짜를 입력하세요 (yyyy-MM-dd): ");
        String date = sc.nextLine();
        System.out.printf("일정 내용을 입력하세요: ");
        String todo = sc.nextLine();

        Schedule schedule = new Schedule(date, todo);
        schedules.add(schedule);

        System.out.printf("일정이 등록되었습니다.\n");
    }

    public void doChange() {
        System.out.printf("일정 변경할 날짜를 입력하세요 (yyyy-MM-dd): ");
        String dateToChange = sc.nextLine();

        boolean found = false;
        for (Schedule schedule : schedules) {
            if (schedule.date.equals(dateToChange)) {
                System.out.printf("현재 일정: %s\n", schedule.todo);
                System.out.printf("새로운 일정을 입력하세요: ");
                String newTodo = sc.nextLine();
                schedule.todo = newTodo;
                found = true;
                System.out.println("일정이 변경되었습니다.");
                break;
            }
        }

        if (!found) {
            System.out.println("해당 날짜의 일정이 없습니다.");
        }

    }

    public void searchDate() {
        System.out.printf("검색할 날짜를 입력하세요 (yyyy-MM-dd): ");
        String searchDate = sc.nextLine();

        boolean found = false;
        for (Schedule schedule : schedules) {
            if (schedule.date.equals(searchDate)) {
                System.out.printf("날짜: %s:  %s\n", schedule.date, schedule.todo);
                found = true;
            }
        }

        if (!found) {
            System.out.println("해당 날짜의 일정이 없습니다.");
        }
    }

    public void searchKeyword() {

        System.out.printf("검색할 키워드를 입력하세요: ");
        String keyword = sc.nextLine();

        boolean found = false;
        for (Schedule schedule : schedules) {
            if (schedule.date.contains(keyword) || schedule.todo.contains(keyword)) {
                System.out.printf("날짜: %s, 일정: %s\n", schedule.date, schedule.todo);
                found = true;
            }
        }

        if (!found) {
            System.out.println("입력한 키워드와 일치하는 일정이 없습니다.");
        }
    }

    public void showList() {
        System.out.print("년도를 입력하세요 (yyyy): ");
        String year = sc.nextLine();
        System.out.print("월을 입력하세요 (MM): ");
        String month = sc.nextLine();


        System.out.println("===== " + year + "년 " + month + "월 일정 목록 =====");
        System.out.println("날짜 | 일정");
        for (Schedule schedule : schedules) {
            // 년도와 월이 입력된 년도와 월과 동일한 경우에 해당하는 일정만 출력합니다.
            if (schedule.date.startsWith(year + "-" + month)) {
                System.out.printf("%s    | %s\n", schedule.date, schedule.todo);
            }
        }
    }
}

