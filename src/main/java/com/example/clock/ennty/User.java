package com.example.clock.ennty;

public class User {

    public int id; // 序号
    public String name; // 姓名
    public String phone; // 电话
    public String classroom;//班级
    public String password;
    public User(){

    }

    public User(int id, String name, String phone, String classroom, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.classroom = classroom;
        this.password = password;
    }

    public User(int id, String name, String phone, String classroom) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", classroom='" + classroom + '\'' +
                '}';
    }
}
