package com.example.audioacquisition.Mine.passbean;

import com.example.audioacquisition.Practice.bean.SceneSort;
import com.example.audioacquisition.Mine.bean.User;

import java.util.List;

public class LoginBean {
    public String status;//状态码，200为正确， 500为手机号或密码错误。
    public User user;// 用户的个人信息，当status不为200时user为null。
    public List<SceneSort> sceneSorts;//场景列表
    public String size;//一共返回多少条数据

}
