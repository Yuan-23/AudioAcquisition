package com.example.audioacquisition.Home.bean;

import java.util.Objects;

public class SceneSort {
    private int id; //主键id
    private String sort_name;  //分类名称
    private int district_id; //对应的地区的id
    private int depart_score; //还没用上
    private int index;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SceneSort sceneSort = (SceneSort) o;
        return id == sceneSort.id &&
                district_id == sceneSort.district_id &&
                depart_score == sceneSort.depart_score &&
                Objects.equals(sort_name, sceneSort.sort_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sort_name, district_id, depart_score);
    }

    @Override
    public String toString() {
        return "SceneSort{" +
                "id=" + id +
                ", sort_name='" + sort_name + '\'' +
                ", district_id=" + district_id +
                ", depart_score=" + depart_score +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSort_name() {
        return sort_name;
    }

    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getDepart_score() {
        return depart_score;
    }

    public void setDepart_score(int depart_score) {
        this.depart_score = depart_score;
    }
}
