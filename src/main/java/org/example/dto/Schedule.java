package org.example.dto;


import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class Schedule {
    public Date date;
    public String todo;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Schedule(String date, String todo) throws ParseException {
        // 문자열을 Date로 변환
        this.date = dateFormat.parse(date);
        this.todo = todo;
    }



    public Schedule(Map<String, Object> row) throws ParseException {
        // 데이터베이스에서 가져온 값을 Date로 변환
        this.date = dateFormat.parse(row.get("date").toString());
        this.todo = (String) row.get("todo");
    }

    // getter for date
    public String getDate() {
        return dateFormat.format(date); // Date 객체를 문자열로 변환하여 반환
    }
    // setter for date


    // getter for todo
    public String getTodo() {
        return todo;
    }

    // setter for todo
    public void setTodo(String todo) {
        this.todo = todo;

    }


}