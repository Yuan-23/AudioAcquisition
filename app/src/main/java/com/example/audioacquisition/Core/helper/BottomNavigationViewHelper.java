package com.example.audioacquisition.Core.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;


public class BottomNavigationViewHelper {

    @SuppressLint({"RestrictedApi", "ResourceAsColor"})
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShifting(false);
                item.setChecked(item.getItemData().isChecked());
//              //容易：ANR，不建议
//                Drawable drawable = loadImageFromNetwork(SharedPreferencesHelper.getAppPicture(menuView.getContext()).getList().get(i));
//                item.setIcon(drawable);
//                item.setIconTintList(ColorStateList.valueOf(R.color.theme2));

//                //后台线程获取url图片：
//                int finalI = i;
//                new Thread(new Runnable() {
//                    Drawable drawable = loadImageFromNetwork(SharedPreferencesHelper.getAppPicture(menuView.getContext())
//                            .getList().get(finalI));
//                    @Override
//                    public void run() {
//                        // post() 特别关键，就是到UI主线程去更新图片
//                        item.post(new Runnable() {
//                            @SuppressLint("ResourceAsColor")
//                            @Override
//                            public void run() {
//
//                                item.setIcon(drawable);
//                                item.setIconTintList(ColorStateList.valueOf(R.color.theme2));
//                            }
//                        });
//                    }
//
//                }).start();

                // set once again checked value, so view will be updated
                //noinspection RestrictedApi

            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }


    private static Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable;
    }


}
