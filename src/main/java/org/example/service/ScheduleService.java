package org.example.service;


import org.example.container.Container;
import org.example.dao.ScheduleDao;
import org.example.dto.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScheduleService {
    private ScheduleDao scheduleDao;

    public ScheduleService() {
        this.scheduleDao = Container.scheduleDao; // ScheduleDao 의존성 주입
    }

    // 일정 추가
    public int addSchedule(String date, String todo) {
        try {
            // Date 객체로 변환
//            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

            Schedule schedule = new Schedule(date, todo);

            return scheduleDao.addSchedule(schedule); // 일정 추가
        } catch (ParseException pe) {
            throw new RuntimeException("날짜 파싱 중 오류: " + pe.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("일정 추가 중 오류: " + e.getMessage());
        }
    }
    // 일정 변경
    public boolean changeSchedule(String dateToChange, String newTodo) {
        return scheduleDao.changeSchedule(dateToChange, newTodo);
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
