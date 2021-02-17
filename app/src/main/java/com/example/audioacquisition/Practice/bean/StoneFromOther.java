package com.example.audioacquisition.Practice.bean;

public class StoneFromOther {
    private int id;
    private int district_id;
    private int type;
    private String file_url;
    private String title;

    @Override
    public String toString() {
        return "StoneFromOther{" +
                "id=" + id +
                ", district_id=" + district_id +
                ", type='" + type + '\'' +
                ", file_url='" + file_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
