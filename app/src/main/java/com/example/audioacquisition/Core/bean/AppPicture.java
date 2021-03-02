package com.example.audioacquisition.Core.bean;

import java.util.List;

public class AppPicture {
    private int id;
    private int district_id;
    private String icon; //交通执法类
    private String flash_page; //刑事治安类
    private String mine; //我的
    private String fax_line; //一线传真
    private String learn; //学习
    private String train; //训练
    private String exam; //考试
    private String mine_top; //我的上面的图片
    private String flash_page_title; //闪页文字
    private int version; //版本号
    private List<String> list;

    @Override
    public String toString() {
        return "AppPicture{" +
                "id=" + id +
                ", district_id=" + district_id +
                ", icon='" + icon + '\'' +
                ", flash_page='" + flash_page + '\'' +
                ", mine='" + mine + '\'' +
                ", fax_line='" + fax_line + '\'' +
                ", learn='" + learn + '\'' +
                ", train='" + train + '\'' +
                ", exam='" + exam + '\'' +
                ", mine_top='" + mine_top + '\'' +
                ", flash_page_title='" + flash_page_title + '\'' +
                ", version=" + version +
                ", list=" + list +
                '}';
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFlash_page_title() {
        return flash_page_title;
    }

    public void setFlash_page_title(String flash_page_title) {
        this.flash_page_title = flash_page_title;
    }

    public String getFax_line() {
        return fax_line;
    }

    public void setFax_line(String fax_line) {
        this.fax_line = fax_line;
    }

    public String getLearn() {
        return learn;
    }

    public void setLearn(String learn) {
        this.learn = learn;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getMine_top() {
        return mine_top;
    }

    public void setMine_top(String mine_top) {
        this.mine_top = mine_top;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFlash_page() {
        return flash_page;
    }

    public void setFlash_page(String flash_page) {
        this.flash_page = flash_page;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }
}
