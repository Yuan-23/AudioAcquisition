package com.example.audioacquisition.Core.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.audioacquisition.Core.bean.AppPicturePassBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.LoginHelper;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Core.widget.RoundTextView;
import com.example.audioacquisition.Mine.activity.LoginActivity;
import com.example.audioacquisition.Mine.passbean.LoginBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

public class SplashActivity extends AppCompatActivity {

    RoundTextView mCountDownTv;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mCountDownTv = (RoundTextView) findViewById(R.id.count_down_tv);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mCountDownTv.getLayoutParams();
        layoutParams.topMargin += getStatusBarHeight();
        mCountDownTv.setLayoutParams(layoutParams);


        mTimer = new CountDownTimer(3 * 1000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) (millisUntilFinished / 1000);
                if (second > 2) {
                    return;
                }
                mCountDownTv.setVisibility(View.VISIBLE);
                mCountDownTv.setText("点击跳过 " + second);
            }

            @Override
            public void onFinish() {
                finish();
                if(SharedPreferencesHelper.getLoginStatus(SplashActivity.this).equals(false)){//未登录
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else if(SharedPreferencesHelper.getLoginStatus(SplashActivity.this).equals(true)){//已经登录
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }.start();

        mCountDownTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                finish();
                if(SharedPreferencesHelper.getLoginStatus(SplashActivity.this).equals(false)){//未登录
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else if(SharedPreferencesHelper.getLoginStatus(SplashActivity.this).equals(true)){//已经登录
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}
