package com.example.audioacquisition.Practice.bean;

public class VideoToTeacher {
    private int id;
    private String title;
    private int district_id;
    private int user_id;
    private String video_url;
    private int type;
    private int flag;
    private String picture;
    private String comment;  //讲评
    private User user;

    @Override
    public String toString() {
        return "VideoToTeacher{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", district_id=" + district_id +
                ", user_id=" + user_id +
                ", video_url='" + video_url + '\'' +
                ", type=" + type +
                ", flag=" + flag +
                ", picture='" + picture + '\'' +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
