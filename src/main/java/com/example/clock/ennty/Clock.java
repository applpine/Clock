package com.example.clock.ennty;

public class Clock {
    public int id; // 序号
    public String username; // 姓名
    public int day;
    public int most_day;
    public String summmary;
    public String key_word;

    public Clock(String username, int day, int most_day, String summmary, String key_word) {
        this.username = username;
        this.day = day;
        this.most_day = most_day;
        this.summmary = summmary;
        this.key_word = key_word;
    }
    public Clock( ) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMost_day() {
        return most_day;
    }

    public void setMost_day(int most_day) {
        this.most_day = most_day;
    }

    public String getSummmary() {
        return summmary;
    }

    public void setSummmary(String summmary) {
        this.summmary = summmary;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", day=" + day +
                ", most_day=" + most_day +
                ", summmary='" + summmary + '\'' +
                ", key_word='" + key_word + '\'' +
                '}';
    }
}
