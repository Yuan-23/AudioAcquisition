package com.example.audioacquisition.Home.bean;

import java.io.File;

public class ThirdBean {
    private String video;
   // private File audio;


    public ThirdBean() {
    }

    public ThirdBean(String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
