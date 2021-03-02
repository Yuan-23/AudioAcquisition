package com.example.audioacquisition.Core.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.adapter.FaxAdapter;
import com.example.audioacquisition.Core.bean.AppPicturePassBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.activity.LoginActivity;
import com.example.audioacquisition.Practice.activity.KindActivity;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

//训练中心

public class PracticeFragment extends Fragment {
    private SwipeRefreshLayout mswipeRefreshLayout;
    LinearLayout kind1;
    LinearLayout kind2;
    ImageView jiaotong;
    ImageView zhian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        kind1 = (LinearLayout) view.findViewById(R.id.practice_kind1);
        kind2 = (LinearLayout) view.findViewById(R.id.practice_kind2);
        jiaotong = (ImageView) view.findViewById(R.id.practice_jiaotong);
        zhian = (ImageView) view.findViewById(R.id.practice_zhian);


        kind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KindActivity.class);
                intent.putExtra("kind", 2);
                startActivity(intent);
            }
        });
        kind2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KindActivity.class);
                intent.putExtra("kind", 3);
                startActivity(intent);
            }
        });


        mswipeRefreshLayout = (SwipeRefreshLayout)  view.findViewById(R.id.practice_swipe);
        // 设置下拉圆圈上的颜色，蓝色、绿色
        mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_blue_dark);
        mswipeRefreshLayout.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mswipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);//背景颜色，需要根据整体UI风格进行更改
        mswipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//刷新图标大

        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                initPicture();//缓存图标
                Toast.makeText(getContext(), "刷新成功！", Toast.LENGTH_SHORT).show();//刷新时要做的事情
                mswipeRefreshLayout.setRefreshing(false);//刷新完成
            }
        });


        initPicture();//缓存图标


        return view;

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
                            SharedPreferencesHelper.setVersion(body.appPicture.getVersion(), getContext());
                            SharedPreferencesHelper.setAppPicture(body.appPicture, getContext());


                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "图标更新中，请重新登录哦。", Toast.LENGTH_SHORT).show();
                        }

                        Glide.with(getContext())
                                .load(SharedPreferencesHelper.getAppPicture(getContext()).getIcon())
                                .into(jiaotong);
                        Glide.with(getContext())
                                .load(SharedPreferencesHelper.getAppPicture(getContext()).getFlash_page())
                                .into(zhian);
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
