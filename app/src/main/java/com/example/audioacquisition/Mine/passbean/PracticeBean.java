package com.example.audioacquisition.Mine.passbean;



/*1.	status: String状态码，200为正确
2.	userNum:用户训练次数 int
3.	userScore：用户训练的平均分数
4.	departNum：部门总的训练次数
5.	departScore：部门平均分数
6.	userRank:用户训练平均分数排名
7.	departRank:部门训练平均分数排名
8.	userNumRank:用户训练次数排名
9.	departNumRank：部门训练次数排名
*/
public class PracticeBean {
    public String status;
    public int userNum;
    public int userScore;
    public int departNum;
    public int departScore;
    public int userRank;
    public int departRank;
    public int userNumRank;
    public int departNumRank;
}
