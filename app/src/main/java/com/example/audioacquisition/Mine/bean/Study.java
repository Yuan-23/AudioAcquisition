package com.example.audioacquisition.Mine.bean;

public class Study {
    String name;
    String score;
    int examid;

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Study(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public Study() {
    }
}
