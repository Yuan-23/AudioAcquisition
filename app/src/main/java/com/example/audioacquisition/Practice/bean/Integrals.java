package com.example.audioacquisition.Practice.bean;

public class Integrals {
    private int id;
    private String date;
    private int user_id;
    private String content;
    private int integral;


    @Override
    public String toString() {
        return "Integrals{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", integral=" + integral +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
