package com.example.audioacquisition.Home.bean;

import java.util.Objects;

public class StageVoice {
    /*id int primary key auto_increment, # 主键
scene_code varchar(50), # 场景代码
police_number varchar(20) unique, # 警号
test_table_id int, # 测试表ID
stage varchar(10), # 阶段
date varchar(30), # 时间
voice varchar(200), # 语音*/
    private int id;
    private int scene_id;
    private int user_id;
    private int exam_id;
    private String stage;
    private String date;
    private String voice;
    private String number;
    private String score;
    private String loudness;
    private String fluency;
    private String definition;
    private String scene_flag;
    private String time;
    private String type;

    @Override
    public String toString() {
        return "StageVoice{" +
                "id=" + id +
                ", scene_id=" + scene_id +
                ", user_id=" + user_id +
                ", exam_id=" + exam_id +
                ", stage='" + stage + '\'' +
                ", date='" + date + '\'' +
                ", voice='" + voice + '\'' +
                ", number='" + number + '\'' +
                ", score='" + score + '\'' +
                ", loudness='" + loudness + '\'' +
                ", fluency='" + fluency + '\'' +
                ", definition='" + definition + '\'' +
                ", scene_flag='" + scene_flag + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLoudness() {
        return loudness;
    }

    public void setLoudness(String loudness) {
        this.loudness = loudness;
    }

    public String getFluency() {
        return fluency;
    }

    public void setFluency(String fluency) {
        this.fluency = fluency;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getScene_flag() {
        return scene_flag;
    }

    public void setScene_flag(String scene_flag) {
        this.scene_flag = scene_flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
