package org.example.service;

import org.example.Util;
import org.example.container.Container;
import org.example.dto.Schedule;
import org.example.dto.User;

import java.lang.reflect.Member;
import java.util.List;

public class ExportService {

    ScheduleService scheduleService;
    UserService userService;

    public ExportService() {
        scheduleService = Container.scheduleService;
        userService = Container.userService;
    }


    public void makeHtml() {
        List<Schedule> schedules = scheduleService.getSchedules(); // 일정 목록 가져오기

        StringBuilder htmlBuilder = new StringBuilder(); // HTML을 생성하기 위한 StringBuilder

        // HTML 헤더 추가
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>일정 목록</title></head>");
        htmlBuilder.append("<body>");

        htmlBuilder.append("<h1>일정 목록</h1>");
        htmlBuilder.append("<ul>");

        // 일정 목록을 순회하면서 HTML 생성
        for (Schedule schedule : schedules) {


            String fileName = schedule + ".html";
            String html = "<meta charset=\"UTF-8\">";
            html += "<div>날짜 : " + schedule.date + "</div>";
            html += "<div>일정 : " + schedule.todo + "</div>";

            Util.writeFileContents("exportHtml/" + fileName, html);

        }
    }
}