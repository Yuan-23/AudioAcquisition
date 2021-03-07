package com.example.audioacquisition.Study.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.audioacquisition.Core.data.KindPermissions;
import com.example.audioacquisition.Mine.fragment.FinishFragment;
import com.example.audioacquisition.Mine.fragment.WaitFragment;
import com.example.audioacquisition.Study.adapter.OtherAdapter;
import com.example.audioacquisition.Study.bean.OtherBean;
import com.example.audioacquisition.R;
import com.example.audioacquisition.Study.fragment.GonganFragment;
import com.example.audioacquisition.Study.fragment.ShengFragment;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import static com.example.audioacquisition.R.drawable.wait;

public class OtherActivity extends FragmentActivity implements View.OnClickListener {
    private Toolbar toolbar;

    private long lastTime;//退出键的最后时间
    private ViewPager mViewPager;// 用来放置界面切换
    private LinearLayout mGongan;
    private LinearLayout mSheng;

    private Fragment gonganfragment;
    private Fragment shengfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_other);
        initView();
        initEvent();
        initViewPage(0);
    }

    /*
     * 判断哪个要显示，及设置按钮图片
     */

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.other_gongan:
                initViewPage(0);
                mViewPager.setCurrentItem(0);
                resetImg();
                mGongan.setBackgroundResource(R.drawable.stage_bg);
                break;
            case R.id.other_sheng:
                initViewPage(1);
                mViewPager.setCurrentItem(1);
                resetImg();
                mSheng.setBackgroundResource(R.drawable.stage_bg);
                break;
            default:
                break;
        }
    }

    /*
     * 初始化配置
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.other_viewpage);
        // 初始化LinearLayout
        mSheng = (LinearLayout) findViewById(R.id.other_sheng);
        mGongan = (LinearLayout) findViewById(R.id.other_gongan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });
        mGongan.setBackgroundResource(R.drawable.stage_bg);
    }

    /*
     * 初始化initViewPage
     */
    private void initViewPage(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();// 创建一个事务
        hideFragment(transaction);// 我们先把所有的Fragment隐藏了，然后下面再开始处理具体要显示的Fragment
        switch (i) {
            case 0:
                if (gonganfragment == null) {
                    gonganfragment = new GonganFragment();
                    transaction.add(R.id.id_content, gonganfragment);
                } else {
                    transaction.show(gonganfragment);
                }
                break;
            case 1:
                if (shengfragment == null) {
                    shengfragment = new ShengFragment();
                    transaction.add(R.id.id_content, shengfragment);// 将Fragment添加到Activity中
                } else {
                    transaction.show(shengfragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();// 提交事务
    }

    private void initEvent() {
        mGongan.setOnClickListener(this);
        mSheng.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        resetImg();
                        mGongan.setBackgroundResource(R.drawable.stage_bg);
                        break;
                    case 1:
                        resetImg();
                        mSheng.setBackgroundResource(R.drawable.stage_bg);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    /*
     * 隐藏所有的Fragment
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (gonganfragment != null) {
            transaction.hide(gonganfragment);
        }
        if (shengfragment != null) {
            transaction.hide(shengfragment);
        }
    }

    /*
     * 把所有背景变暗
     */
    private void resetImg() {
        mSheng.setBackgroundResource(R.drawable.viewpager_bg);
        mGongan.setBackgroundResource(R.drawable.viewpager_bg);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - lastTime <= 2000) {
                finish();
            } else {
                lastTime = System.currentTimeMillis();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }




}
