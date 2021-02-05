package com.example.audioacquisition.Core.data;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.example.audioacquisition.Core.activity.MainActivity;

public class KindPermissions {
    //申请录音权限

    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO
    };
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static String[] PERMISSIONS_STORAGE = {
            //Android6.0以后操作系统的动态权限申请
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };



//    public static void RequestStoragePermissions(Activity activity) {//申请播放权限
//        int permission = ActivityCompat.checkSelfPermission(activity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permission != PackageManager.PERMISSION_GRANTED) { // 请求权限
//            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE,
//                    EXTERNAL_STORAGE_REQ_CODE);
//        }
//    }

    /*
     * 申请录音权限*/
    public static void RequestAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }




    /**
     * 用于Android6.0以后的操作系统，动态申请存储的读写权限
     *
     * @param context
     */
    public static void RequestVedioPermissions(Activity context) {
        //用于Android6.0以后的操作系统，动态申请存储的读写权限
        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, PERMISSIONS_STORAGE, 1);
        }
    }


}
