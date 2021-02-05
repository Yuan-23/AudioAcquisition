package com.example.audioacquisition.Mine.bean;

public class User {
    /*id int primary key auto_increment, # 主键
area_code varchar(10), # 地区编码
police_number varchar(20) unique, # 警号
password varchar(20), # 密码
police_name varchar(50), # 警察姓名
administrators_id int not null,  # 管理员ID*/
    private int id;
    private String police_number;
    private String password;
    private String name;
    private int district_id;
    private int department_id;
    private long learn_time;
    private int learn_num;
    private double learn_score;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", police_number='" + police_number + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", district_id=" + district_id +
                ", department_id=" + department_id +
                ", learn_time=" + learn_time +
                ", learn_num=" + learn_num +
                ", learn_score=" + learn_score +
                '}';
    }

    public double getLearn_score() {
        return learn_score;
    }

    public void setLearn_score(double learn_score) {
        this.learn_score = learn_score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolice_number() {
        return police_number;
    }

    public void setPolice_number(String police_number) {
        this.police_number = police_number;
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

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public long getLearn_time() {
        return learn_time;
    }

    public void setLearn_time(long learn_time) {
        this.learn_time = learn_time;
    }

    public int getLearn_num() {
        return learn_num;
    }

    public void setLearn_num(int learn_num) {
        this.learn_num = learn_num;
    }
}
