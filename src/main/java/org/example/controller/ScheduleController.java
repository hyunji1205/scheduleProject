package org.example.controller;

import org.example.container.Container;
import org.example.dto.Schedule;
import org.example.dto.User;
import org.example.service.ScheduleService;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ScheduleController extends Controller {
    private Scanner sc;
    private List<Schedule> schedules;
    private String cmd;
    private ScheduleService scheduleService; // 필드 이름 변경
    private UserService userService;
    private Session session;


    public ScheduleController(Scanner sc) {
        this.sc = sc;
        this.schedules = new ArrayList<>();
        this.scheduleService = Container.getScheduleService(); // 의존성 주입
        this.userService = Container.getUserService();
        this.session = Container.getSession();
    }

    public void doAction(String cmd) {
        this.cmd = cmd;
        switch (cmd) {
            case "일정등록":
                doWrite();
                break;
            case "일정변경":
                doChange();
                break;
            case "일정검색":
                showSearchOptions();
                break;
            case "일정목록":
                findByMonth();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }


    private void showSearchOptions() {
        System.out.println("== 일정 검색 ==");
        System.out.println(" ----------------------------------");
        System.out.println("1. 날짜로 검색");
        System.out.println("2. 키워드로 검색");
        System.out.println(" ----------------------------------");
        System.out.print("메뉴를 선택하세요: ");
        String searchOption = sc.nextLine();

        switch (searchOption) {
            case "날짜로 검색":
                searchByDate();
                break;

            case "키워드로 검색":
                searchByKeyword();
                break;

            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                break;
        }
    }

    private void doWrite() {
        System.out.println(" == 일정 등록 == ");
        System.out.println(" ----------------------------------");
        System.out.printf("일정 날짜를 입력하세요 (yyyy-MM-dd): ");
        String date = sc.nextLine();

        if (!isValidDate(date)) {
            System.out.println("유효하지 않은 날짜 형식입니다.");
            return;
        }

        System.out.printf("일정 내용을 입력하세요: ");
        String todo = sc.nextLine();

        try {
            scheduleService.addSchedule(date, todo); // 일정 추가
            System.out.println("일정이 등록되었습니다.");
        } catch (Exception e) {
            System.err.println("일정 등록 중 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void doChange() {
        System.out.println(" == 일정 변경 == ");
        System.out.println(" ----------------------------------");
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
        System.out.println("날짜로 검색합니다.");
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
            for (Schedule schedule : matchingSchedules) {
                System.out.printf("날짜: %s, 일정: %s\n", schedule.getDate(), schedule.getTodo());
            }
        }
    }

    private void searchByKeyword() {
        System.out.println("키워드로 검색합니다.");
        System.out.printf("검색할 키워드를 입력하세요: ");
        String keyword = sc.nextLine();

        List<Schedule> matchingSchedules = scheduleService.getSchedulesByKeyword(keyword);

        if (matchingSchedules.isEmpty()) {
            System.out.println("일치하는 일정이 없습니다.");
        } else {
            for (Schedule schedule : matchingSchedules) {
                System.out.printf("날짜: %s, 일정: %s\n", schedule.getDate(), schedule.getTodo());
            }
        }
    }
    private void showList() {
        List<Schedule> forPrintSchedules = scheduleService.getSchedules();

        for (int i = forPrintSchedules.size() - 1; i >= 0; i--) {
            Schedule schedule = forPrintSchedules.get(i); // 역순으로 가져오기

            String scheduleDate = schedule.getDate(); // 일정 날짜
            String scheduleContent = schedule.getTodo(); // 일정 내용

            // 일정 정보 출력
            System.out.printf("%s  | %s\n", scheduleDate, scheduleContent);
        }
    }

    private void findByMonth() {
        System.out.println(" == 일정 목록 == ");
        System.out.println(" ----------------------------------");
        System.out.printf("원하는 년도를 입력하세요 (yyyy): ");
        String year = sc.nextLine();

        System.out.printf("원하는 월을 입력하세요 (MM): ");
        String month = sc.nextLine();

        if (!isValidYearAndMonth(year, month)) {
            System.out.printf("유효하지 않은 연도 또는 월 형식입니다.");
            return;
        }

        List<Schedule> schedules = scheduleService.getSchedulesByYearAndMonth(year, month);

        if (schedules.isEmpty()) {
            System.out.printf("%s년 %s월에 일정이 없습니다.\n", year, month);
        } else {
            System.out.printf("===== %s년 %s월 일정  목록 =====\n", year, month);
            schedules.forEach(schedule ->
                    System.out.printf("날짜: %s, 일정: %s\n", schedule.getDate(), schedule.getTodo())
            );
        }
    }
    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}"); // 간단한 날짜 형식 검사
    }

    private boolean isValidYearAndMonth(String year, String month) {
        return year.matches("\\d{4}") && month.matches("\\d{2}"); // 간단한 연도 및 월 검사
    }
}
