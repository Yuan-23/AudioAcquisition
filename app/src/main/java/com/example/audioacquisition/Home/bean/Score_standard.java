package com.example.audioacquisition.Home.bean;

import java.util.Objects;

public class Score_standard {
    private int id;
    private int scene_id;
    private String standard_url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score_standard that = (Score_standard) o;
        return id == that.id &&
                scene_id == that.scene_id &&
                Objects.equals(standard_url, that.standard_url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, scene_id, standard_url);
    }

    @Override
    public String toString() {
        return "Score_standard{" +
                "id=" + id +
                ", scene_id=" + scene_id +
                ", standard_url='" + standard_url + '\'' +
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

    public String getStandard_url() {
        return standard_url;
    }

    public void setStandard_url(String standard_url) {
        this.standard_url = standard_url;
    }
}
