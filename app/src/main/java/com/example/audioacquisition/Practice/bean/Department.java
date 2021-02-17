package com.example.audioacquisition.Practice.bean;

import java.util.Objects;

public class Department {
    private int id;
    private String depart_name;
    private int district_id;
    private int depart_score;
    private int learn_num;
    private double learn_score;
    private int index;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depart_name='" + depart_name + '\'' +
                ", district_id=" + district_id +
                ", depart_score=" + depart_score +
                ", learn_num=" + learn_num +
                ", learn_score=" + learn_score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                district_id == that.district_id &&
                depart_score == that.depart_score &&
                learn_num == that.learn_num &&
                Double.compare(that.learn_score, learn_score) == 0 &&
                Objects.equals(depart_name, that.depart_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, depart_name, district_id, depart_score, learn_num, learn_score);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getLearn_score() {
        return learn_score;
    }

    public void setLearn_score(double learn_score) {
        this.learn_score = learn_score;
    }

    public int getLearn_num() {
        return learn_num;
    }

    public void setLearn_num(int learn_num) {
        this.learn_num = learn_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
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
