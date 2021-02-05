package com.example.audioacquisition.Home.bean;

import java.io.File;

public class DetailBean {
    private String context;
    private int scenecode;
    private String stage;
    private String number;
    private int sort;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getScenecode() {
        return scenecode;
    }

    public void setScenecode(int scenecode) {
        this.scenecode = scenecode;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public DetailBean() {
    }
}
