package org.example.dto;


import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class Schedule {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public Date date; // java.sql.Date 타입
    public String todo;

    public Schedule(Date date, String todo) {
        this.date = date; // 이미 Date 객체인 경우 그대로 할당
        this.todo = todo;
    }



    public Schedule(Map<String, Object> row) {
        if (row != null) {
            Object dateValue = row.get("date");

            if (dateValue instanceof Date) { // java.sql.Date 확인
                this.date = (Date) dateValue; // 캐스팅
            } else if (dateValue instanceof String) { // 문자열인 경우
                try {
                    this.date = dateFormat.parse(dateValue.toString()); // 변환
                } catch (Exception e) { // 예외 처리
                    this.date = new Date(); // 예외 시 현재 날짜로 설정
                }
            } else {
                this.date = new Date(); // 기본값
            }

            // todo 필드 설정
            this.todo = row.get("todo") != null ? row.get("todo").toString() : "No Description";
        } else {
            // 예외 또는 기본값 설정
            this.date = new Date();
            this.todo = "No Description";
        }
    }
    public String getDate() {
        // java.util.Date로 변환 후 포맷팅
        return dateFormat.format(new java.util.Date(this.date.getTime()));
    }

}