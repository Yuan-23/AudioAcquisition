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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.adapter.StudynewAdapter;
import com.example.audioacquisition.Core.bean.BannerBean;
import com.example.audioacquisition.Core.bean.FaxBean;
import com.example.audioacquisition.Core.bean.StudyNewBean;
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
import java.util.List;

//学习中心

public class StudyFragment extends Fragment {

    private RecyclerView StudyNewRv;
    private List<FaxBean> faxBeanList = new ArrayList<>();
    private SwipeRefreshLayout mswipeRefreshLayout;//下拉刷新
    private Banner banner;
    ArrayList<String> imagPath = new ArrayList<>();
    private LinearLayout study11;
    private LinearLayout study12;
    private LinearLayout study13;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        banner = view.findViewById(R.id.study_banner);
        StudyNewRv = view.findViewById(R.id.studynew_rv);

        study11 = view.findViewById(R.id.study_11);
        study12 = view.findViewById(R.id.study_12);
        study13 = view.findViewById(R.id.study_13);


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
        });

        initBanner();//为轮播图赋值
        initstudy();//为推荐内容赋值


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

    //初始化科普贴内容
    private void initstudy() {
        OkGo.<StudyNewBean>post(UrlConstants.StudyNew)
                .params("district_id", SharedPreferencesHelper.getUserBean(getContext()).getDistrict_id())
                .execute(new GsonCallback<StudyNewBean>(StudyNewBean.class) {
                    @Override
                    public void onSuccess(Response<StudyNewBean> response) {
                        StudyNewBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                for (int i = 0; i < body.myLearnRecommends.size(); i++) {
                                    FaxBean faxBean = new FaxBean();
                                    faxBean.setFaxpic(body.myLearnRecommends.get(i).getPicture_url());
                                    faxBean.setFaxname(body.myLearnRecommends.get(i).getTitle());
                                    faxBean.setFaxid(body.myLearnRecommends.get(i).getId());
                                    faxBeanList.add(faxBean);
                                }
                                StudynewAdapter studynewAdapter = new StudynewAdapter(faxBeanList);
                                StudyNewRv.setAdapter(studynewAdapter); //dialog.show
                                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);//第二个参数为网格的列数
                                StudyNewRv.setLayoutManager(layoutManager);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "暂无推荐~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<StudyNewBean> response) {
                        super.onError(response);
                        System.out.println("测试1：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }


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
