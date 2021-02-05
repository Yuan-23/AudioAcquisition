package com.example.audioacquisition.Core.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.blankj.utilcode.util.GsonUtils;
import com.example.audioacquisition.Core.data.SharedPreConstants;
import com.example.audioacquisition.Core.data.SharedPreferencesUtil;
import com.example.audioacquisition.Mine.bean.User;

public class SharedPreferencesHelper {

    /*用户账号*/
    public static String getUserAccount(Context context) {
        return SharedPreferencesUtil.getString(context, SharedPreConstants.USER_ACCOUNT, "");
    }

    public static void setUserAccount(String userAccount, Context context) {
        SharedPreferencesUtil.putString(context, SharedPreConstants.USER_ACCOUNT, userAccount);
    }

    /*用户密码*/
    public static String getUserPassWord(Context context) {
        return SharedPreferencesUtil.getString(context, SharedPreConstants.USER_PASSWORD, "");
    }

    public static void setUserPassWord(String userPassWord, Context context) {
        SharedPreferencesUtil.putString(context, SharedPreConstants.USER_PASSWORD, userPassWord);
    }

    /*场景ID*/
    public static int getSceneCode(Context context) {
        return SharedPreferencesUtil.getInt(context, SharedPreConstants.SCENECODE, 0);
    }

    public static void setSceneCode(int sceneCode, Context context) {
        SharedPreferencesUtil.putInt(context, SharedPreConstants.SCENECODE, sceneCode);
    }

    /*场景ID*/
    public static int getAreaCode(Context context) {
        return SharedPreferencesUtil.getInt(context, SharedPreConstants.AREACODE, 0);
    }

    public static void setAreaCode(int areaCode, Context context) {
        SharedPreferencesUtil.putInt(context, SharedPreConstants.AREACODE, areaCode);
    }

    /*登录情况*/
    public static Boolean getLoginStatus(Context context) {
        return SharedPreferencesUtil.getBoolean(context, SharedPreConstants.LOGIN_STATE, false);
    }

    public static void setLoginStatus(boolean loginStatus, Context context) {
        SharedPreferencesUtil.putBoolean(context, SharedPreConstants.LOGIN_STATE, loginStatus);
    }

    /*收集阶段录音情况*/

    public static int getRecordStatus(Context context) {
        return SharedPreferencesUtil.getInt(context, SharedPreConstants.RECORDSTATUS, 0);
    }

    public static void setRecordStatus(int recordstatus, Context context) {
        SharedPreferencesUtil.putInt(context, SharedPreConstants.RECORDSTATUS, recordstatus);
    }

    /*收集阶段播放情况*/

    public static int getPlayStatus(Context context) {
        return SharedPreferencesUtil.getInt(context, SharedPreConstants.PLAYSTATUS, 0);
    }

    public static void setPlayStatus(int playstatus, Context context) {
        SharedPreferencesUtil.putInt(context, SharedPreConstants.PLAYSTATUS, playstatus);
    }


    /*用户id*/
    public static int getUserId(Context context) {
        return SharedPreferencesUtil.getInt(context, SharedPreConstants.USER_ID, -1);
    }

    public static void setUserId(int userId, Context context) {
        SharedPreferencesUtil.putInt(context, SharedPreConstants.USER_ID, userId);
    }

        /*用户类*/
        public static void setUserBean(User user ,Context context) {
        SharedPreferencesUtil.putString(context, SharedPreConstants.USERBEAN, GsonUtils.toJson(user));

    }

    public static User getUserBean(Context context) {
        String jsonStr = SharedPreferencesUtil.getString(context, SharedPreConstants.USERBEAN, "");
        return GsonUtils.fromJson(jsonStr, User.class);
    }




}
