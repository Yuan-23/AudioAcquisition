package com.example.audioacquisition.Home.bean;

import java.sql.Date;
import java.util.Objects;

public class Exam {
    private int id; //主键
    private int scene_id; //场景主键
    private int user_id; //用户主键
    private int department_id; //部门主键
    private String exam_name; //考试名称
    private Date date; //日期
    private int answer_flag; //是否回答
    private Double score; //一共要多少分
    private int count_flag; //一共有多少条语音要录
    private int exam_record_id; //
    private User user;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", scene_id=" + scene_id +
                ", user_id=" + user_id +
                ", department_id=" + department_id +
                ", exam_name='" + exam_name + '\'' +
                ", date=" + date +
                ", answer_flag=" + answer_flag +
                ", score=" + score +
                ", count_flag=" + count_flag +
                ", exam_record_id=" + exam_record_id +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getExam_record_id() {
        return exam_record_id;
    }

    public void setExam_record_id(int exam_record_id) {
        this.exam_record_id = exam_record_id;
    }

    public int getCount_flag() {
        return count_flag;
    }

    public void setCount_flag(int count_flag) {
        this.count_flag = count_flag;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAnswer_flag() {
        return answer_flag;
    }

    public void setAnswer_flag(int answer_flag) {
        this.answer_flag = answer_flag;
    }
}
