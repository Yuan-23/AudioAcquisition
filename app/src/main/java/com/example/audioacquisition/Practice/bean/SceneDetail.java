package com.example.audioacquisition.Practice.bean;

import java.util.List;
import java.util.Objects;

public class SceneDetail {
    private int id; //主键
    private int scene_id; //场景的主键
    private String stage; //详情的阶段（1，2，3，4）
    private String video_url; //分段视频的地址
    private String stage_name; //该阶段的名字
    private String time_nodes; //该阶段的时间节点
    private List<SceneDetailContent> sceneDetailContents; //该阶段的详细信息

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SceneDetail that = (SceneDetail) o;
        return id == that.id &&
                scene_id == that.scene_id &&
                Objects.equals(stage, that.stage) &&
                Objects.equals(video_url, that.video_url) &&
                Objects.equals(stage_name, that.stage_name) &&
                Objects.equals(time_nodes, that.time_nodes) &&
                Objects.equals(sceneDetailContents, that.sceneDetailContents);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, scene_id, stage, video_url, stage_name, time_nodes, sceneDetailContents);
    }

    @Override
    public String toString() {
        return "SceneDetail{" +
                "id=" + id +
                ", scene_id=" + scene_id +
                ", stage='" + stage + '\'' +
                ", video_url='" + video_url + '\'' +
                ", stage_name='" + stage_name + '\'' +
                ", time_nodes='" + time_nodes + '\'' +
                ", sceneDetailContents=" + sceneDetailContents +
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public String getTime_nodes() {
        return time_nodes;
    }

    public void setTime_nodes(String time_nodes) {
        this.time_nodes = time_nodes;
    }

    public List<SceneDetailContent> getSceneDetailContents() {
        return sceneDetailContents;
    }

    public void setSceneDetailContents(List<SceneDetailContent> sceneDetailContents) {
        this.sceneDetailContents = sceneDetailContents;
    }
}
