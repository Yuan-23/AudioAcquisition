package com.example.audioacquisition.Study.passbean;

import com.example.audioacquisition.Practice.bean.Scene;
import com.example.audioacquisition.Practice.bean.SceneDetail;

import java.util.List;

public class TeachPassBean {
    private int size;
    private List<SceneDetail> sceneDetail;
    private String status;
    private Scene scene;

    public TeachPassBean() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
