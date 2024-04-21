package org.example.dto;

public class Schedule {
    public String date;
    public String todo;


    public Schedule(String date, String todo) {
        this.date = date;
        this.todo = todo;
    }

    // getter for date
    public String getDate() {
        return date;
    }

    // setter for date
    public void setDate(String date) {
        this.date = date;
    }

    // getter for todo
    public String getTodo() {
        return todo;
    }

    // setter for todo
    public void setTodo(String todo) {
        this.todo = todo;

    }
}



