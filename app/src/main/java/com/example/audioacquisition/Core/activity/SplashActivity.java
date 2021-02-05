package com.example.audioacquisition.Core.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.audioacquisition.Core.widget.RoundTextView;
import com.example.audioacquisition.R;

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
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();

        mCountDownTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                finish();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
