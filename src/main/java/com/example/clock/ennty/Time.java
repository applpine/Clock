package com.example.clock.ennty;

public class Time {
    public String time;
    public String username;
    public int id; // 序号


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Time(String time, String username) {
        this.time = time;
        this.username = username;
    }
    public Time() {

    }

    @Override
    public String toString() {
        return "Time{" +
                "time='" + time + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                '}';
    }
}
