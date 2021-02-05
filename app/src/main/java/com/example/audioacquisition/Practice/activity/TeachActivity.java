package com.example.audioacquisition.Practice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.audioacquisition.Core.data.KindPermissions;
import com.example.audioacquisition.Home.fragment.HorizontalFragment;
import com.example.audioacquisition.Practice.fragment.TrafficFragment;
import com.example.audioacquisition.R;

import java.util.ArrayList;

public class TeachActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;

    private HorizontalScrollView ths;
    private LinearLayout tliner;
    private ViewPager tview_pager;
    private String[] ttitles;
    int flag = 0;
    private ArrayList<TextView> ttitlesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        //初始化
        ths = (HorizontalScrollView) findViewById(R.id.teach_hs);
        tliner = (LinearLayout) findViewById(R.id.teach_liner);
        tview_pager = (ViewPager) findViewById(R.id.teach_view_pager);
        ttitles = new String[]{"交通", "治安"};

        if (Build.VERSION.SDK_INT >= 23) {
            KindPermissions.RequestAudioPermissions(this);
            KindPermissions.RequestVedioPermissions(this);
        }

        inittitles();

        tview_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TrafficFragment.getInstance(ttitles[position]);
            }

            @Override
            public int getCount() {
                return ttitles.length;
            }
        });

        setOnClickListener();
    }


    private void setOnClickListener() {
        tview_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直被调用。
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // 此方法是页面跳转完后被调用
            @Override
            public void onPageSelected(int position) {

                // 标题变色,用循环改变标题颜色,通过判断来决定谁红谁灰;
                // 举例:第一个的下标是position是1
                for (int i = 0; i < ttitles.length; i++) {
                    if (i == position) {
                        ttitlesView.get(i).setTextColor(Color.YELLOW);
                        ttitlesView.get(i).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        ttitlesView.get(i).setTextColor(Color.WHITE);
                        ttitlesView.get(i).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                }
                // 标题滑动功能
                int width = ttitlesView.get(position).getWidth();
                int totalWidth = (width + 20) * position;
                ths.scrollTo(totalWidth, 0);
            }

            // 此方法是在状态改变的时候调用。
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    flag = 1;
                }
            }
        });
    }

    private void inittitles() {
        //装标题控件的集合
        ttitlesView = new ArrayList<>();
        for (int i = 0; i < ttitles.length; i++) {
            TextView textView = new TextView(TeachActivity.this);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(16);
            textView.setBackgroundResource(R.drawable.title2_bg);
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            if (i == 0) {
                textView.setTextColor(Color.YELLOW);
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
            textView.setText(ttitles[i]);
            textView.setId(i);//把循环的i设置给textview的下标;
            textView.setOnClickListener(this);

            //LinearLayout中的孩子的定位参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(215, 10, 215, 10);//设置左上右下四个margin值;
            //layoutParams是让linearLayout知道如何摆放自己孩子的位置的;
            tliner.addView(textView, layoutParams);
            ttitlesView.add(textView);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        tview_pager.setCurrentItem(id);
    }
}
