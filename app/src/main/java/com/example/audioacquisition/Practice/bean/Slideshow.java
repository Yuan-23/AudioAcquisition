package com.example.audioacquisition.Practice.bean;

public class Slideshow {
    private int id;
    private int district_id;
    private String show_url;
    private String show_name;

    @Override
    public String toString() {
        return "Slideshow{" +
                "id=" + id +
                ", district_id=" + district_id +
                ", show_url='" + show_url + '\'' +
                ", show_name='" + show_name + '\'' +
                '}';
    }

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
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

    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }
}
