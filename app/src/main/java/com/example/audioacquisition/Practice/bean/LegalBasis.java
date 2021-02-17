package com.example.audioacquisition.Practice.bean;

public class LegalBasis {
    private int id;
    private int scene_id;
    private String content;

    @Override
    public String toString() {
        return "LegalBasis{" +
                "id=" + id +
                ", scene_id=" + scene_id +
                ", content='" + content + '\'' +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
