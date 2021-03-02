package com.example.audioacquisition.Core.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.activity.MainActivity;
import com.example.audioacquisition.Core.bean.AppPicturePassBean;
import com.example.audioacquisition.Core.data.SharedPreferencesUtil;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.LoginHelper;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.activity.ChangeActivity;
import com.example.audioacquisition.Mine.activity.CourceActivity;
import com.example.audioacquisition.Mine.activity.InfoActivity;
import com.example.audioacquisition.Mine.activity.LoginActivity;
import com.example.audioacquisition.Mine.activity.PracticeActivity;
import com.example.audioacquisition.Mine.activity.StudyActivity;
import com.example.audioacquisition.Mine.activity.SuggestionActivity;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */


public class MineFragment extends Fragment {
    private SwipeRefreshLayout mswipeRefreshLayout;//下拉刷新
    private LinearLayout mlogin;
    private LinearLayout mchange;
    private LinearLayout mcource;
    private LinearLayout mpractice;
    private LinearLayout mstudy;
    private LinearLayout minfo;
    private LinearLayout msuggestion;
    private TextView mname;
    private TextView mlogintv;
    private ImageView mimage;
    private CircleImageView mportrait;

    private Banner banner;
    //存轮播图
    ArrayList<Integer> imagPath = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mlogin = (LinearLayout) view.findViewById(R.id.mine_login_ll);
        mchange = (LinearLayout) view.findViewById(R.id.mine_change_ll);
        mstudy = (LinearLayout) view.findViewById(R.id.mine_study_ll);
        minfo = (LinearLayout) view.findViewById(R.id.mine_info_ll);
        msuggestion = (LinearLayout) view.findViewById(R.id.mine_suggestion_ll);
        mcource = (LinearLayout) view.findViewById(R.id.mine_course_ll);
        mpractice = (LinearLayout) view.findViewById(R.id.mine_practice_ll);
        mname = (TextView) view.findViewById(R.id.mine_username);
        mlogintv = (TextView) view.findViewById(R.id.mine_login_tv);
        mimage = (ImageView) view.findViewById(R.id.mine_image);
        mportrait = (CircleImageView) view.findViewById(R.id.mine_portrait);

        initPicture();

        mswipeRefreshLayout = view.findViewById(R.id.mine_swipe);
        // 设置下拉圆圈上的颜色，蓝色、绿色
        mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light);
        mswipeRefreshLayout.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mswipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);//背景颜色，需要根据整体UI风格进行更改
        mswipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//刷新图标大


        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (SharedPreferencesHelper.getLoginStatus(getContext()).equals(true)) {//已登录

                    mname.setText(SharedPreferencesHelper.getUserBean(getContext()).getNickname());
                    Glide.with(getContext())
                            .load(SharedPreferencesHelper.getAppPicture(getContext()).getMine_top())
                            .into(mimage);
                    Glide.with(getContext())
                            .load(SharedPreferencesHelper.getUserAvatarUrl(getContext()))
                            .into(mportrait);
                    mlogintv.setText("退        出");
                } else {
                    mname.setText("您还未登录");
                    mlogintv.setText("登        录");
                }

                Toast.makeText(getActivity(), "刷新成功！", Toast.LENGTH_SHORT).show();//刷新时要做的事情
                mswipeRefreshLayout.setRefreshing(false);//刷新完成
            }
        });


        if (SharedPreferencesHelper.getLoginStatus(getContext()).equals(true)) {//已登录

            mname.setText(SharedPreferencesHelper.getUserBean(getContext()).getNickname());
            Glide.with(getContext())
                    .load(SharedPreferencesHelper.getAppPicture(getContext()).getMine_top())
                    .into(mimage);
            Glide.with(getContext())
                    .load(SharedPreferencesHelper.getUserAvatarUrl(getContext()))
                    .into(mportrait);
            mlogintv.setText("退        出");
        } else {
            mname.setText("您还未登录");
            mlogintv.setText("登        录");
        }

        mname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mlogintv.getText().equals("登        录")) {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                } else if (mlogintv.getText().equals("退        出")) {
                    SharedPreferencesHelper.setUserAccount("", getContext());
                    SharedPreferencesHelper.setUserPassWord("", getContext());
                    SharedPreferencesHelper.setUserId(-1, getContext());
                    SharedPreferencesHelper.setUserBean(null, getContext());
                    SharedPreferencesHelper.setLoginStatus(false, getContext());
                    SharedPreferencesHelper.setDepartmentid(-1, getContext());
                    SharedPreferencesHelper.setUserAvatarUrl(null, getContext());
                    LoginHelper.logout(getContext());
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent2);

                    //退出时销毁自己这个碎片，防止登录页点击返回进入APP
                    getActivity().onBackPressed();

                }
            }
        });
        mchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), ChangeActivity.class);
                startActivity(intent2);
            }
        });
        mcource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), CourceActivity.class);
                startActivity(intent3);
            }
        });
        mpractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity(), PracticeActivity.class);
                startActivity(intent4);
            }
        });
        mstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getActivity(), StudyActivity.class);
                startActivity(intent5);
            }
        });
        minfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent6);
            }
        });
        msuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(getActivity(), SuggestionActivity.class);
                startActivity(intent7);
            }
        });
        return view;
    }

    //下拉刷新
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                mswipeRefreshLayout.setRefreshing(false);
                //添加需要刷新的内容，比如重新执行一遍布局文件。
            }
        }, 2000); // 2秒后发送消息，停止刷新
    }

    public void initPicture() {
        OkGo.<AppPicturePassBean>post(UrlConstants.Picture)
                .params("district_id", 2)//默认青冈县为2
                .execute(new GsonCallback<AppPicturePassBean>(AppPicturePassBean.class) {
                    @Override
                    public void onSuccess(Response<AppPicturePassBean> response) {
                        AppPicturePassBean body = response.body();
                        System.out.println("图标测试：" + body.appPicture.toString());
                        if (body.status.equals("200")) {
                            //初次为version赋值并且缓存图标,如果版本号有所改变则重新缓存图标和Version的值，否则不用更新图标
//                            if ((SharedPreferencesHelper.getVersion(getContext()) == -1) ||
//                                    (SharedPreferencesHelper.getVersion(getContext()) != body.appPicture.getVersion())) {
//                            SharedPreferencesHelper.setVersion(body.appPicture.getVersion(), getContext());
                            SharedPreferencesHelper.setAppPicture(body.appPicture, getContext());


                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "图标更新中，请重新登录哦。", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<AppPicturePassBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });


    }


}
