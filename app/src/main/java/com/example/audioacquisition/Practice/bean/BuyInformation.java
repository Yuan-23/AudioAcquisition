package com.example.audioacquisition.Practice.bean;

public class BuyInformation {
    /*create table buy_information(
id int primary key auto_increment,
area_code varchar(20),
area_name varchar(50),
scene_number varchar(20),
account_number varchar(20),
aaa varchar(1),
bbb varchar(1),
ccc varchar(1),
ddd varchar(1),
eee varchar(1)
);*/
    private int id;
    private int district_id;
    private int scene_number;
    private int account_number;

    @Override
    public String toString() {
        return "BuyInformation{" +
                "id=" + id +
                ", district_id=" + district_id +
                ", scene_number=" + scene_number +
                ", account_number=" + account_number +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getScene_number() {
        return scene_number;
    }

    public void setScene_number(int scene_number) {
        this.scene_number = scene_number;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }
}
