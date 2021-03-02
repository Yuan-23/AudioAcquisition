package com.example.audioacquisition.Practice.bean;

import java.util.Objects;

public class SceneDetailContent {
    private int id; //主键
    private int scene_detail_id; //阶段详情的主键
    private String content; //阶段详情内容（需要显示出来的）
    private String number; //详情的两部分，和之前一样
    private String video_url; //分段场景视频
    private String start_time;  //开始时间
    private String end_time; //结束时间

    @Override
    public String toString() {
        return "SceneDetailContent{" +
                "id=" + id +
                ", scene_detail_id=" + scene_detail_id +
                ", content='" + content + '\'' +
                ", number='" + number + '\'' +
                ", video_url='" + video_url + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                '}';
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScene_detail_id() {
        return scene_detail_id;
    }

    public void setScene_detail_id(int scene_detail_id) {
        this.scene_detail_id = scene_detail_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
