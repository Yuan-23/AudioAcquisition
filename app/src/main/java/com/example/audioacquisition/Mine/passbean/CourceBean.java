package com.example.audioacquisition.Mine.passbean;

import com.example.audioacquisition.Practice.bean.Scene;

import java.util.List;

public class CourceBean {
    public String status;
    public List<Scene> scene;//learnFlag = 0表示没学习过，为1表示已经学过了
    public int size;
    public int rank;//排名
    public long learnTime;//用户的学习时间描述
}
