package org.example.dao;

import org.example.dto.Schedule;
import org.example.db.DBConnection;
import org.example.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ScheduleDao {
    public List<Schedule> schedules;
    private DBConnection dbConnection;

    public ScheduleDao() {
        schedules = new ArrayList<>();
        dbConnection = Container.getDBConnection();

    }


    // 일정 추가
    public void addSchedule(Schedule schedule) {

        schedules.add(schedule); // 일정 추가
    }


    // 모든 일정 반환
    public List<Schedule> getSchedules() {
        String sql = "SELECT * FROM schedules"; // 쿼리 문자열
        List<Schedule> schedules = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = dbConnection.selectRows(sql); // 쿼리 실행
            if (rows != null) { // null 체크
                for (Map<String, Object> row : rows) {
                    schedules.add(new Schedule(row)); // Schedule 객체로 변환
                }
            } else {
                System.out.println("일정이 없습니다."); // 결과가 비었을 때
            }
        } catch (Exception e) { // 예외 처리
            System.err.println("쿼리 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 예외 로그
        }

        return schedules; // 결과 반환
    }



    // 일정 업데이트
    public boolean changeSchedule(String date, Schedule updatedSchedule) {
        List<Schedule> schedule = findByDate(date);

        if (schedule != null) {
            schedules.set(schedules.indexOf(schedule), updatedSchedule); // 업데이트
            return true; // 성공
        }

        return false; // 실패
    }

    // 날짜로 검색
    public List<Schedule> findByDate(String date) {
        List<Schedule> result = new ArrayList<>();

        for (Schedule schedule : schedules) {
            if (schedule.getDate().equals(date)) {
                result.add(schedule);
            }
        }

        return result;
    }

    // 키워드로 검색
    public List<Schedule> searchByKeyword(String keyword) {
        List<Schedule> result = new ArrayList<>();

        for (Schedule schedule : schedules) {
            if (schedule.getTodo().contains(keyword)) {
                result.add(schedule); // 키워드와 일치하는 일정 추가
            }
        }

        return result; // 결과 반환
    }

    // 월별 일정 반환
    public List<Schedule> findByMonth(String year, String month) {
        List<Schedule> schedules = new ArrayList<>();
        String sql = String.format("SELECT * FROM schedules WHERE YEAR(date) = %s AND MONTH(date) = %s", year, month); // 쿼리 문자열

        try {
            List<Map<String, Object>> rows = dbConnection.selectRows(sql); // 쿼리 실행
            if (rows != null) { // null 체크
                for (Map<String, Object> row : rows) { // 결과 루프
                    schedules.add(new Schedule(row)); // Schedule 객체로 변환
                }
            } else {
                System.out.println("해당 월의 일정이 없습니다."); // 결과가 비었을 때
            }
        } catch (Exception e) { // 예외 처리
            System.err.println("쿼리 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 예외 로그
        }

        return schedules; // 결과 반환
    }
}

