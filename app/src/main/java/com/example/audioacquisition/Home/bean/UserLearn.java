package com.example.audioacquisition.Home.bean;

public class UserLearn {
    private int id;
    private int user_id;
    private int scene_id;

    @Override
    public String toString() {
        return "UserLearn{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", scene_id=" + scene_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScene_id() {
        return scene_id;
    }

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }
}
