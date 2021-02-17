package com.example.audioacquisition.Practice.bean;

public class SceneDetailContent {
    private int id; //主键
    private int scene_detail_id; //阶段详情的主键
    private String content; //阶段详情内容（需要显示出来的）
    private String number; //详情的两部分，和之前一样
    private String video_url; //分段场景视频

    @Override
    public String toString() {
        return "SceneDetailContent{" +
                "id=" + id +
                ", scene_detail_id=" + scene_detail_id +
                ", content='" + content + '\'' +
                ", number='" + number + '\'' +
                ", video_url='" + video_url + '\'' +
                '}';
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
