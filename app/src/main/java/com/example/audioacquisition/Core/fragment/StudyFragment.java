package com.example.audioacquisition.Core.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.bean.BannerBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Study.activity.OtherActivity;
import com.example.audioacquisition.Study.activity.PolicyActivity;
import com.example.audioacquisition.Study.activity.TeachActivity;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

//学习中心

public class StudyFragment extends Fragment {

    private RecyclerView recyclerView;
    // private List<FirstAD> firstADArrayList = new ArrayList<>();
    private SwipeRefreshLayout mswipeRefreshLayout;//下拉刷新
    private LinearLayout search;
    private Banner banner;
    ArrayList<String> imagPath = new ArrayList<>();
    private LinearLayout study11;
    private LinearLayout study12;
    private LinearLayout study13;
//    private LinearLayout study21;
//    private LinearLayout study22;
//    private LinearLayout study23;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        study11 = view.findViewById(R.id.study_11);
        study12 = view.findViewById(R.id.study_12);
        study13 = view.findViewById(R.id.study_13);

//        study21 = view.findViewById(R.id.study_21);
//        study22 = view.findViewById(R.id.study_22);
//        study23 = view.findViewById(R.id.study_23);


        study11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(getContext(), TeachActivity.class);
                startActivity(intent11);
            }
        });
        study12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12 = new Intent(getContext(), PolicyActivity.class);
                startActivity(intent12);
            }
        });
        study13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent13 = new Intent(getContext(), OtherActivity.class);
                startActivity(intent13);
            }
        });

//        study21.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent21 = new Intent(getContext(), TypicalActivity.class);
//                startActivity(intent21);
//            }
//        });
//        study22.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent22 = new Intent(getContext(), FaxActivity.class);
//                startActivity(intent22);
//            }
//        });
//        study23.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent23 = new Intent(getContext(), TestActivity.class);
//                startActivity(intent23);
//            }
//        });


        //  search = view.findViewById(R.id.search_ll);
        banner = view.findViewById(R.id.study_banner);
        recyclerView = view.findViewById(R.id.study_rv);


        mswipeRefreshLayout = view.findViewById(R.id.study_swipe);
        // 设置下拉圆圈上的颜色，蓝色、绿色
        mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light);
        mswipeRefreshLayout.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mswipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);//背景颜色，需要根据整体UI风格进行更改
        mswipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//刷新图标大

        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新成功！", Toast.LENGTH_SHORT).show();//刷新时要做的事情
                mswipeRefreshLayout.setRefreshing(false);//刷新完成
            }
        });//  mswipeRefreshLayout.setRefreshing(false)为false时 停止刷新效果

        //       initad();
//        AdAdapter adadapter = new AdAdapter(firstADArrayList);
//        recyclerView.setAdapter(adadapter); //dialog.show
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);

//        search.setOnClickListener(new View.OnClickListener() {//搜索
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), SearchActivity.class);
//                startActivity(intent);
//            }
//        });
        if (SharedPreferencesHelper.getLoginStatus(getContext()) == true) {
            initBanner();//为轮播图赋值
        } else {
            Toast.makeText(getContext(), "还未登录。", Toast.LENGTH_SHORT).show();
        }


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

//    //初始化科普贴内容
//    private void initad() {
//        FirstAD firstAD1 = new FirstAD("养猫小常识:猫咪喝水的习惯你知道吗？", "作者：猫猫多", R.drawable.cat2);
//        firstADArrayList.add(firstAD1);
//
//        FirstAD firstAD2 = new FirstAD("鸭妈妈孵出小鸭的必备条件！", "作者：一朵小太阳", R.drawable.duck);
//        firstADArrayList.add(firstAD2);
//
//        FirstAD firstAD3 = new FirstAD("什么鸟类的养殖价值最大呢？", "作者：希达", R.drawable.bird);
//        firstADArrayList.add(firstAD3);
//    }


    //轮播图
    private void initBanner() {

        OkGo.<BannerBean>post(UrlConstants.Banner)
                .params("district_id", SharedPreferencesHelper.getUserBean(getContext()).getDistrict_id())
                .execute(new GsonCallback<BannerBean>(BannerBean.class) {
                    @Override
                    public void onSuccess(Response<BannerBean> response) {
                        BannerBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                for (int i = 0; i < body.slideshow.size(); i++) {
                                    imagPath.add(body.slideshow.get(i).getShow_url());
                                }

                                //设置图片加载器
                                banner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
                                    @Override
                                    public void displayImage(Context context, Object path, ImageView imageView) {
                                        Glide.with(context.getApplicationContext())
                                                .load(path)
                                                .into(imageView);

                                    }
                                });

                                //显示图片张数1/8  否则为点点显示
                                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                                //设置轮播间隔时间
                                banner.setDelayTime(3000);
                                //设置是否为自动轮播，默认是true
                                banner.isAutoPlay(true);
                                //设置指示器的位置，圆点，居中显示
                                banner.setIndicatorGravity(BannerConfig.CENTER);
                                banner.setScrollBarSize(8);//8张图
                                banner.setImages(imagPath).start();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "信息有误，请重试~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<BannerBean> response) {
                        super.onError(response);
                        System.out.println("测试1：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
