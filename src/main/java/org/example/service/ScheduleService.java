package org.example.service;


import org.example.dao.ScheduleDao;
import org.example.dto.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScheduleService {
    private ScheduleDao scheduleDao;

    public ScheduleService(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao; // ScheduleDao 의존성 주입
    }

    // 일정 추가
    public int addSchedule(String dateStr, String todo) {
        try {
            // 날짜 문자열을 java.util.Date로 변환
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);

            // Schedule 객체 생성
            Schedule schedule = new Schedule(date, todo);
            return scheduleDao.addSchedule(schedule); // ScheduleDao를 통해 추가
        } catch (ParseException e) {
            throw new RuntimeException("날짜 형식 오류: " + dateStr, e);
        } catch (Exception e) {
            throw new RuntimeException("일정 추가 중 오류: " + e.getMessage(), e);
        }
    }
    // 일정 변경
    public boolean changeSchedule(String dateToChange, String newTodo) {
        return scheduleDao.changeSchedule(dateToChange, newTodo); // ScheduleDao의 변경 메소드 호출
    }
    // 모든 일정 반환
    public List<Schedule> getSchedules() {
        return scheduleDao.getSchedules(); // ScheduleDao의 모든 일정 반환
    }

    // 날짜로 일정 검색
    public List<Schedule> getSchedulesByDate(String searchDate) {
        return scheduleDao.findByDate(searchDate); // ScheduleDao를 통한 날짜 검색
    }

    // 키워드로 일정 검색
    public List<Schedule> getSchedulesByKeyword(String keyword) {
        return scheduleDao.searchByKeyword(keyword); // ScheduleDao를 통한 키워드 검색
    }

    // 월별 일정 검색
    public List<Schedule> getSchedulesByYearAndMonth(String year, String month) {
        return scheduleDao.findByMonth(year, month); // ScheduleDao를 통한 월별 검색
    }

}
