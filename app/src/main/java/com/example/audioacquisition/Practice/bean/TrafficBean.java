package com.example.audioacquisition.Practice.bean;

public class TrafficBean {
    private String videoname;
    private int sceneSortId;

    public TrafficBean(String videoname) {
        this.videoname = videoname;
    }

    public int getSceneSortId() {
        return sceneSortId;
    }

    public TrafficBean(String videoname, int sceneSortId) {
        this.videoname = videoname;
        this.sceneSortId = sceneSortId;
    }

    public void setSceneSortId(int sceneSortId) {
        this.sceneSortId = sceneSortId;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public TrafficBean() {
    }
}
