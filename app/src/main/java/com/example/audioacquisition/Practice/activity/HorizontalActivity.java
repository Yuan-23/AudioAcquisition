package com.example.audioacquisition.Practice.activity;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.audioacquisition.Practice.fragment.HorizontalFragment;
import com.example.audioacquisition.R;

import java.util.ArrayList;

public class HorizontalActivity extends AppCompatActivity implements View.OnClickListener {
    private HorizontalScrollView hs;
    private LinearLayout liner;
    private ViewPager view_pager;
    private String[] titles;
    int flag = 0;
    private ArrayList<TextView> titlesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        //初始化
        hs = (HorizontalScrollView) findViewById(R.id.hs);
        liner = (LinearLayout) findViewById(R.id.liner);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        titles = new String[]{"练习", "模考"};

        if (Build.VERSION.SDK_INT >= 23) {
            KindPermissions.RequestAudioPermissions(this);
            KindPermissions.RequestVedioPermissions(this);
        }

        Intent intent = getIntent();
        int code = intent.getIntExtra("code", -1);
        int areacode = intent.getIntExtra("area", -1);
        inittitles();

        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return HorizontalFragment.getInstance(titles[position], code, areacode);
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });

        setOnClickListener();
    }


    private void setOnClickListener() {
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直被调用。
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // 此方法是页面跳转完后被调用
            @Override
            public void onPageSelected(int position) {

                // 标题变色,用循环改变标题颜色,通过判断来决定谁红谁灰;
                // 举例:第一个的下标是position是1
                for (int i = 0; i < titles.length; i++) {
                    if (i == position) {
                        titlesView.get(i).setTextColor(Color.YELLOW);
                        titlesView.get(i).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        titlesView.get(i).setTextColor(Color.WHITE);
                        titlesView.get(i).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                }
                // 标题滑动功能
                int width = titlesView.get(position).getWidth();
                int totalWidth = (width + 20) * position;
                hs.scrollTo(totalWidth, 0);
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
        titlesView = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            TextView textView = new TextView(HorizontalActivity.this);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(16);
            textView.setBackgroundResource(R.drawable.title_bg);
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            if (i == 0) {
                textView.setTextColor(Color.YELLOW);
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
            textView.setText(titles[i]);
            textView.setId(i);//把循环的i设置给textview的下标;
            textView.setOnClickListener(this);

            //LinearLayout中的孩子的定位参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(215, 10, 215, 10);//设置左上右下四个margin值;
            //layoutParams是让linearLayout知道如何摆放自己孩子的位置的;
            liner.addView(textView, layoutParams);
            titlesView.add(textView);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        view_pager.setCurrentItem(id);
    }
}
