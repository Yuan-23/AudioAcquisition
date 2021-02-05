package com.example.audioacquisition.Mine.bean;

import java.util.Objects;

public class exam {
    private int id;
    private int scene_id;
    private int user_id;
    private int department_id;
    private String date;
    private int answer_flag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        exam exam = (exam) o;
        return id == exam.id &&
                scene_id == exam.scene_id &&
                user_id == exam.user_id &&
                department_id == exam.department_id &&
                answer_flag == exam.answer_flag &&
                Objects.equals(date, exam.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, scene_id, user_id, department_id, date, answer_flag);
    }

    @Override
    public String toString() {
        return "exam{" +
                "id=" + id +
                ", scene_id=" + scene_id +
                ", user_id=" + user_id +
                ", department_id=" + department_id +
                ", date='" + date + '\'' +
                ", answer_flag=" + answer_flag +
                '}';
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAnswer_flag() {
        return answer_flag;
    }

    public void setAnswer_flag(int answer_flag) {
        this.answer_flag = answer_flag;
    }
}
