package org.example.controller;

import org.example.container.Container;
import org.example.dto.Schedule;
import org.example.service.ScheduleService;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.container.Container.scheduleService;

public class ScheduleController extends Controller {
    private Scanner sc;
    private List<Schedule> schedules;
    private String cmd;
    private String actionMethodName;
    private ScheduleService ScheduleService;
    private UserService userService;

    public ScheduleController(Scanner sc) {

        this.sc = sc;
        this.schedules = new ArrayList<>();
        userService = Container.userService;
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "등록":
                if ( isLogined() == false ) {
                    System.out.println("로그인 후 이용해주세요.");
                    break;
                }
                doWrite();
                break;
            case "변경":
                doChange();
                break;

            case "검색":
                showSearchOptions();
                break;

            case "목록":
                showList();
                break;

            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    private void showSearchOptions() {
        System.out.println("== 일정 검색 ==");
        System.out.println("1. 날짜로 검색");
        System.out.println("2. 키워드로 검색");
        String searchOption = sc.nextLine();

        switch (searchOption) {
            case "1":
                searchByDate();
                break;

            case "2":
                searchByKeyword();
                break;

            default:
                System.out.println("올바른 옵션을 선택해주세요.");
                break;
        }
    }

    public void makeTestData() {
        System.out.println("테스트를 위한 게시물 데이터를 생성합니다");

        scheduleService.addSchedule("2024-04-13", "재민생일");
        scheduleService.addSchedule("2024-04-20", "오늘");
        scheduleService.addSchedule("2024-04-23", "재민돼지");
    }

    private void doWrite() {
        System.out.printf("일정 날짜를 입력하세요 (yyyy-MM-dd): ");
        String date = sc.nextLine();

        if (!isValidDate(date)) {
            System.out.println("유효하지 않은 날짜 형식입니다.");
            return;
        }

        System.out.printf("일정 내용을 입력하세요: ");
        String todo = sc.nextLine();

        scheduleService.addSchedule(date, todo);

        System.out.println("일정이 등록되었습니다.");
    }

    private void doChange() {
        System.out.printf("일정 변경할 날짜를 입력하세요 (yyyy-MM-dd): ");
        String dateToChange = sc.nextLine();

        if (!isValidDate(dateToChange)) {
            System.out.println("유효하지 않은 날짜 형식입니다.");
            return;
        }

        System.out.printf("새로운 일정 내용을 입력하세요: ");
        String newTodo = sc.nextLine();

        boolean updated = scheduleService.changeSchedule(dateToChange, newTodo);

        if (updated) {
            System.out.println("일정이 변경되었습니다.");
        } else {
            System.out.printf("%s 날짜의 일정을 찾을 수 없습니다.\n", dateToChange);
        }
    }

    private void searchByDate() {
        System.out.printf("검색할 날짜를 입력하세요 (yyyy-MM-dd): ");
        String searchDate = sc.nextLine();

        if (!isValidDate(searchDate)) {
            System.out.println("유효하지 않은 날짜 형식입니다.");
            return;
        }

        List<Schedule> matchingSchedules = scheduleService.getSchedulesByDate(searchDate);

        if (matchingSchedules.isEmpty()) {
            System.out.println("해당 날짜의 일정이 없습니다.");
        } else {
            matchingSchedules.forEach(schedule ->
                System.out.printf("날짜: %s, 일정: %s\n", schedule.date, schedule.todo));
        }
    }

    private void searchByKeyword() {
        System.out.printf("검색할 키워드를 입력하세요: ");
        String keyword = sc.nextLine();

        List<Schedule> matchingSchedules = scheduleService.getSchedulesByKeyword(keyword);

        if (matchingSchedules.isEmpty()) {
            System.out.println("일치하는 일정이 없습니다.");
        } else {
            matchingSchedules.forEach(schedule ->
                    System.out.printf("날짜: %s, 일정: %s\n", schedule.date, schedule.todo));
        }
    }

    private void showList() {
        System.out.print("년도를 입력하세요 (yyyy): ");
        String year = sc.nextLine();
        System.out.print("월을 입력하세요 (MM): ");
        String month = sc.nextLine();

        if (!isValidYearAndMonth(year, month)) {
            System.out.println("유효하지 않은 연도 또는 월 형식입니다.");
            return;
        }

        List<Schedule> schedules = scheduleService.getSchedulesByYearAndMonth(year, month);

        if (schedules.isEmpty()) {
            System.out.printf("해당 연도 및 월의 일정이 없습니다.\n");
        } else {
            System.out.println("===== " + year + "년 " + month + "월 일정 목록 =====");
            schedules.forEach(schedule ->
                    System.out.printf("%s    | %s\n", schedule.date, schedule.todo));
        }
    }

    private boolean isValidDate(String date) {
        // 간단한 날짜 형식 검사 (예: "yyyy-MM-dd")
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private boolean isValidYearAndMonth(String year, String month) {
        return year.matches("\\d{4}") && month.matches("\\d{2}");
    }
}

