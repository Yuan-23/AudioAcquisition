package com.example.audioacquisition.Home.passbean;

import java.util.List;

public class ScoreBean {
    public String status;
    public String score;
    public List<String> stageScore;
    public List<Integer> scoreFlag;//判断每个阶段是否计算完成
    public int flag;//判断是否计算完成
}
