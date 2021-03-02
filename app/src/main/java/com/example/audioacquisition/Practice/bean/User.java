package com.example.audioacquisition.Practice.bean;

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
    private int integral;
    private String head_portrait;
    private String phone_number;
    private String nickname;
    private String department_name;


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
                ", integral=" + integral +
                ", head_portrait='" + head_portrait + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", nickname='" + nickname + '\'' +
                ", department_name='" + department_name + '\'' +
                '}';
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
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
