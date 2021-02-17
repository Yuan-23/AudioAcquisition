package com.example.audioacquisition.Practice.bean;

public class District {
    /*id int primary key auto_increment, # 主键
area_code varchar(10) unique, # 地区代码
area_name varchar(50), # 地区名字
administrators_id int not null,  # 管理员ID*/
    private int id; //主键
    private String area_code; //地区代码
    private String area_name; //地区名
    private String delete_flag; //还没用上

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", area_code='" + area_code + '\'' +
                ", area_name='" + area_name + '\'' +
                ", delete_flag='" + delete_flag + '\'' +
                '}';
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
}
