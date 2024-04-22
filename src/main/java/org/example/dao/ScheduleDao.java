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
    public int addSchedule(Schedule schedule) {
        try {
            // SQL 구문을 직접 작성
            String sql = String.format("INSERT INTO schedules (date, todo) VALUES ('%s', '%s')",
                    schedule.getDate(),
                    schedule.getTodo());

            return dbConnection.insert(sql); // 데이터베이스 삽입
        } catch (Exception e) {
            System.err.println("일정 추가 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return -1; // 오류 발생 시 -1 반환
        }
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
    public boolean changeSchedule(String date, String newTodo) {
        try {
            // SQL 구문을 직접 작성
            String sql = String.format("UPDATE schedules SET todo = '%s' WHERE date = '%s'",
                    newTodo,
                    date);

            int affectedRows = dbConnection.update(sql); // SQL 업데이트 실행
            return affectedRows > 0; // 업데이트된 행 수가 0 이상인지 확인
        } catch (Exception e) { // 예외 처리
            System.err.println("일정 변경 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return false; // 오류 발생 시 false 반환
        }
    }
    // 날짜로 검색
    public List<Schedule> findByDate(String date) {
        // 쿼리 문자열을 포매팅하여 동적 생성
        String sql = String.format("SELECT * FROM schedules WHERE date = '%s'", date);

        // 결과 리스트
        List<Schedule> result = new ArrayList<>();

        try {
            // 쿼리 실행
            List<Map<String, Object>> rows = dbConnection.selectRows(sql);

            // 결과가 비었을 때
            if (rows == null || rows.isEmpty()) {
                System.out.printf("해당 날짜(%s)의 일정이 없습니다.\n", date);
            } else {
                // 결과를 Schedule 객체로 변환
                for (Map<String, Object> row : rows) {
                    result.add(new Schedule(row));
                }
            }
        } catch (Exception e) { // 예외 처리
            System.err.println("쿼리 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 예외 로그
        }

        return result; // 결과 반환
    }
    // 키워드로 검색
    public List<Schedule> searchByKeyword(String keyword) {
        String sql = String.format("SELECT * FROM schedules WHERE todo LIKE '%%%s%%'", keyword); // 문자열 연결
        List<Schedule> result = new ArrayList<>(); // 결과 리스트

        try {
            List<Map<String, Object>> rows = dbConnection.selectRows(sql); // 쿼리 실행
            if (rows == null || rows.isEmpty()) { // 결과가 비었을 때
                System.out.println("해당 키워드로 검색된 일정이 없습니다.");
            } else {
                for (Map<String, Object> row : rows) { // 결과 루프
                    result.add(new Schedule(row)); // Schedule 객체로 변환
                }
            }
        } catch (Exception e) { // 예외 처리
            System.err.println("쿼리 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 예외 로그
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

