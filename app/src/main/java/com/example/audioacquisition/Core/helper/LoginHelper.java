package com.example.audioacquisition.Core.helper;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.audioacquisition.Core.data.DialogUtils;
import com.example.audioacquisition.Core.data.SharedPreConstants;
import com.example.audioacquisition.Core.data.SharedPreferencesUtil;
import com.example.audioacquisition.Core.event.LoginEvent;
import com.example.audioacquisition.Core.event.LogoutEvent;
import com.example.audioacquisition.Mine.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;


/**
 * @description:
 * @author: NoOne
 * @date: 2020-04-26 13:33
 */
public class LoginHelper {


    public static boolean isLogin(Context context) {
        return isLogin(context, false);
    }

    public static boolean isLogin(Context context, boolean showDialog) {
        boolean isLogin = SharedPreferencesUtil.getBoolean(context, SharedPreConstants.LOGIN_STATE, false);
        if (!isLogin && showDialog) {
            showLoginDialog(context);
        }
        return isLogin;
    }

    public static void login(Context context) {
        EventBus.getDefault().post(new LoginEvent());
        SharedPreferencesUtil.putBoolean(context, SharedPreConstants.LOGIN_STATE, true);
    }

    public static void logout(Context context) {
        EventBus.getDefault().post(new LogoutEvent());
        SharedPreferencesUtil.putBoolean(context, SharedPreConstants.LOGIN_STATE, false);
    }

    public static void showLoginDialog(final Context context) {
        DialogUtils.showDialog(context, "提示", "您还未登录，请先登录", "登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        }, "取消", null);
    }
}
