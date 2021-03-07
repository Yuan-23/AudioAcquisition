package com.example.audioacquisition.Mine.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.fragment.FinishFragment;
import com.example.audioacquisition.Mine.fragment.WaitFragment;
import com.example.audioacquisition.Mine.passbean.CourceBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import static com.example.audioacquisition.R.drawable.wait;

public class CourceActivity extends FragmentActivity implements View.OnClickListener {
    private TextView CourceNumber;
    private TextView CourceSort;
    private Toolbar toolbar;
    private ImageView waitimage;
    private ImageView finifhimage;

    private long lastTime;//退出键的最后时间
    private ViewPager mViewPager;// 用来放置界面切换
    private LinearLayout mFinish;
    private LinearLayout mWait;

    private Fragment finishfragment;
    private Fragment waitfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cource);
        initdata();
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
            case R.id.bottom_wait:
                initViewPage(0);
                mViewPager.setCurrentItem(0);
                resetImg();
                mWait.setBackgroundResource(R.drawable.fragment_bg);
                waitimage.setImageResource(R.drawable.wait);
                break;
            case R.id.bottom_finish:
                initViewPage(1);
                mViewPager.setCurrentItem(1);
                resetImg();
                mFinish.setBackgroundResource(R.drawable.fragment_bg);
                finifhimage.setImageResource(R.drawable.finish);
                break;
            default:
                break;
        }
    }

    /*
     * 初始化配置
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.cource_viewpage);
        // 初始化LinearLayout
        mFinish = (LinearLayout) findViewById(R.id.bottom_finish);
        mWait = (LinearLayout) findViewById(R.id.bottom_wait);
        finifhimage = (ImageView) findViewById(R.id.bottom_finish_imag);
        waitimage = (ImageView) findViewById(R.id.bottom_wait_imag);
        CourceNumber = (TextView) findViewById(R.id.cource_number);
        CourceSort = (TextView) findViewById(R.id.cource_sort);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });
        waitimage.setImageResource(wait);
        finifhimage.setImageResource(R.drawable.finish);
        mWait.setBackgroundResource(R.drawable.fragment_bg);
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
                if (waitfragment == null) {
                    waitfragment = new WaitFragment();
                    transaction.add(R.id.id_content, waitfragment);
                } else {
                    transaction.show(waitfragment);
                }
                break;
            case 1:
                if (finishfragment == null) {
                    finishfragment = new FinishFragment();
                    transaction.add(R.id.id_content, finishfragment);// 将Fragment添加到Activity中
                } else {
                    transaction.show(finishfragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();// 提交事务
    }

    private void initEvent() {
        mFinish.setOnClickListener(this);
        mWait.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        resetImg();
                        mWait.setBackgroundResource(R.drawable.fragment_bg);
                        waitimage.setImageResource(wait);
                        break;
                    case 1:
                        resetImg();
                        mFinish.setBackgroundResource(R.drawable.fragment_bg);
                        finifhimage.setImageResource(R.drawable.finish);
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
        if (finishfragment != null) {
            transaction.hide(finishfragment);
        }
        if (waitfragment != null) {
            transaction.hide(waitfragment);
        }
    }

    /*
     * 把所有背景变暗
     */
    private void resetImg() {
        mFinish.setBackgroundResource(R.drawable.home_bg);
        mWait.setBackgroundResource(R.drawable.home_bg);

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

    public void initdata() {

        OkGo.<CourceBean>post(UrlConstants.Cource)
                .params("user_id", SharedPreferencesHelper.getUserId(CourceActivity.this))
                .execute(new GsonCallback<CourceBean>(CourceBean.class) {
                    @Override
                    public void onSuccess(Response<CourceBean> response) {
                        CourceBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                CourceNumber.setText(body.learnTime + " h");
                                CourceSort.setText(body.rank + " 名");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(CourceActivity.this, "信息有误，请重试~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<CourceBean> response) {
                        super.onError(response);
                        System.out.println("测试1：网络失败");
                        Toast.makeText(CourceActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}



