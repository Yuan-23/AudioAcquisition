package com.example.audioacquisition.Home.bean;

import java.util.Objects;

public class TestTable {
    /*id int primary key auto_increment, # 主键
scene_code varchar(50), # 场景编号
police_number varchar(20), # 警号
date varchar(30), # 日期*/
    private int id;
    private String scene_code;
    private String police_number;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTable testTable = (TestTable) o;
        return id == testTable.id &&
                Objects.equals(scene_code, testTable.scene_code) &&
                Objects.equals(police_number, testTable.police_number) &&
                Objects.equals(date, testTable.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, scene_code, police_number, date);
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "id=" + id +
                ", scene_code='" + scene_code + '\'' +
                ", police_number='" + police_number + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScene_code() {
        return scene_code;
    }

    public void setScene_code(String scene_code) {
        this.scene_code = scene_code;
    }

    public String getPolice_number() {
        return police_number;
    }

    public void setPolice_number(String police_number) {
        this.police_number = police_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
