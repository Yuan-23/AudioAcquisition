package com.example.audioacquisition.Home.bean;

import java.util.List;

public class ClassVoice {
    private List<StageVoice> stageVoices;
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<StageVoice> getStageVoices() {
        return stageVoices;
    }

    public void setStageVoices(List<StageVoice> stageVoices) {
        this.stageVoices = stageVoices;
    }
}
