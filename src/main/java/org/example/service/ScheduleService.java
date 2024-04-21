package org.example.service;


import org.example.container.Container;
import org.example.dao.ScheduleDao;
import org.example.dto.Schedule;

import java.util.List;

public class ScheduleService {
    private ScheduleDao scheduleDao;

    public ScheduleService() {
        this.scheduleDao = Container.scheduleDao; // ScheduleDao 의존성 주입
    }

    // 일정 추가
    public void addSchedule(String date, String todo) {
        if (date == null || todo == null) {
            throw new IllegalArgumentException("날짜와 일정 내용은 null일 수 없습니다.");
        }
        Schedule newSchedule = new Schedule(date, todo);
        scheduleDao.addSchedule(newSchedule);
    }

    // 일정 변경
    public boolean changeSchedule(String dateToChange, String newTodo) {
        List<Schedule> schedules = scheduleDao.findByDate(dateToChange); // ScheduleDao를 통해 날짜로 검색
        if (schedules != null && !schedules.isEmpty()) { // 리스트가 비어있지 않음 확인
            Schedule schedule = schedules.get(0); // 첫 번째 일정 가져오기
            schedule.setTodo(newTodo); // 일정 내용 변경
            return true; // 성공
        }
        return false; // 실패 (일정을 찾을 수 없는 경우)
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
