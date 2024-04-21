package org.example.dao;

import org.example.dto.Schedule;

import java.util.ArrayList;
import java.util.List;


public class ScheduleDao {
    public List<Schedule> schedules;

    public ScheduleDao() {
        schedules = new ArrayList<>();


    }


    // 일정 추가
    public void addSchedule(Schedule schedule) {

        schedules.add(schedule); // 일정 추가
    }


    // 모든 일정 반환
    public List<Schedule> getSchedules() {
        return schedules; // 모든 일정 반환
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
    public List<Schedule>  findByDate(String date) {
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
        List<Schedule> result = new ArrayList<>();

        for (Schedule schedule : schedules) {
            if (schedule.getDate().startsWith(year + "-" + month)) {
                result.add(schedule); // 해당 월의 일정 추가
            }
        }

        return result; // 결과 반환
    }


}

