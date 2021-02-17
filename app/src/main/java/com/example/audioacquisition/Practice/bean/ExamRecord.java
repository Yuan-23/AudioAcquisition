package com.example.audioacquisition.Practice.bean;

public class ExamRecord {
    private int id;
    private int district_id;
    private String exam_name;

    @Override
    public String toString() {
        return "ExamRecord{" +
                "id=" + id +
                ", district_id=" + district_id +
                ", exam_name='" + exam_name + '\'' +
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

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }
}
