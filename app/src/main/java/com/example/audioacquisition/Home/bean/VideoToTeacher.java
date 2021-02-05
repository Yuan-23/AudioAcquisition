package com.example.audioacquisition.Home.bean;

public class VideoToTeacher {
    private int id;
    private String title;
    private int district_id;
    private int user_id;
    private String video_url;

    @Override
    public String toString() {
        return "VideoToTeacher{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", district_id=" + district_id +
                ", user_id=" + user_id +
                ", video_url='" + video_url + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
