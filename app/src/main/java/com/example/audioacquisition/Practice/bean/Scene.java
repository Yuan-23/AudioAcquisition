package com.example.audioacquisition.Practice.bean;

import java.util.List;

public class Scene {
    private int id; //主键
    private String scene_name; //场景名称
    private int scene_sort_id; //所属分类的id
    private String video_url; //视频的地址
    private int stageNum; //该场景有多少个阶段
    private String standard_url; //评分标准的地址
    private String teacher_url; //教学视频的地址
    private String exam_url; //考试视频的地址
    private int learnFlag;
    private String sceneDetail; //场景的详情
    private List<LegalBasis> legalBases;//
    private List<Essential> essentials;

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", scene_name='" + scene_name + '\'' +
                ", scene_sort_id=" + scene_sort_id +
                ", video_url='" + video_url + '\'' +
                ", stageNum=" + stageNum +
                ", standard_url='" + standard_url + '\'' +
                ", teacher_url='" + teacher_url + '\'' +
                ", exam_url='" + exam_url + '\'' +
                ", learnFlag=" + learnFlag +
                ", sceneDetail='" + sceneDetail + '\'' +
                ", legalBases=" + legalBases +
                ", essentials=" + essentials +
                '}';
    }

    public List<Essential> getEssentials() {
        return essentials;
    }

    public void setEssentials(List<Essential> essentials) {
        this.essentials = essentials;
    }

    public String getTeacher_url() {
        return teacher_url;
    }

    public void setTeacher_url(String teacher_url) {
        this.teacher_url = teacher_url;
    }

    public String getExam_url() {
        return exam_url;
    }

    public void setExam_url(String exam_url) {
        this.exam_url = exam_url;
    }

    public List<LegalBasis> getLegalBases() {
        return legalBases;
    }

    public void setLegalBases(List<LegalBasis> legalBases) {
        this.legalBases = legalBases;
    }

    public int getLearnFlag() {
        return learnFlag;
    }

    public void setLearnFlag(int learnFlag) {
        this.learnFlag = learnFlag;
    }

    public String getStandard_url() {
        return standard_url;
    }

    public void setStandard_url(String standard_url) {
        this.standard_url = standard_url;
    }

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScene_name() {
        return scene_name;
    }

    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }

    public int getScene_sort_id() {
        return scene_sort_id;
    }

    public void setScene_sort_id(int scene_sort_id) {
        this.scene_sort_id = scene_sort_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getSceneDetail() {
        return sceneDetail;
    }

    public void setSceneDetail(String sceneDetail) {
        this.sceneDetail = sceneDetail;
    }
}
