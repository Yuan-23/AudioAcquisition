package com.example.audioacquisition.Practice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.audioacquisition.Practice.adapter.HomeAdapter;
import com.example.audioacquisition.Core.bean.HomeBean;
import com.example.audioacquisition.Core.bean.KindBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class KindActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<HomeBean> homeBeanList = new ArrayList<>();
    private SwipeRefreshLayout mswipeRefreshLayout;
    int kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);
        recyclerView = findViewById(R.id.home_rv);

        Intent intent = getIntent();
        kind = intent.getIntExtra("kind", -1);

        initforum();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });


        mswipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_swipe);
        // 设置下拉圆圈上的颜色，蓝色、绿色
        mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_blue_dark);
        mswipeRefreshLayout.setDistanceToTriggerSync(100);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mswipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);//背景颜色，需要根据整体UI风格进行更改
        mswipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//刷新图标大

        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HomeAdapter homeAdapter1 = new HomeAdapter(homeBeanList);
                homeBeanList.clear();
                if (recyclerView.getChildCount() > 0) {
                    recyclerView.removeAllViews();
                    homeAdapter1.notifyDataSetChanged();
                    recyclerView.setAdapter(homeAdapter1); //dialog.show
                }
                initforum();
                Toast.makeText(KindActivity.this, "刷新成功！", Toast.LENGTH_SHORT).show();//刷新时要做的事情
                mswipeRefreshLayout.setRefreshing(false);//刷新完成
            }
        });


        HomeAdapter homeAdapter = new HomeAdapter(homeBeanList);
        recyclerView.setAdapter(homeAdapter); //dialog.show
        LinearLayoutManager layoutManager = new LinearLayoutManager(KindActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void initforum() {
        System.out.println("类别为：" + kind);
        OkGo.<KindBean>post(UrlConstants.Kind)
                .params("sceneSortId", kind)//类别
                .execute(new GsonCallback<KindBean>(KindBean.class) {
                    @Override
                    public void onSuccess(Response<KindBean> response) {
                        KindBean body = response.body();
                        if (body.status.equals("200")) {
                            for (int i = 0; i < body.scene.size(); i++) {//最多访问一页的条数
                                System.out.println("数据ID第" + i + "次输出:" + body.scene.get(i).getId());
                                try {
                                    HomeBean homeBean = new HomeBean();
                                    homeBean.setName(body.scene.get(i).getScene_name());
                                    homeBean.setScode(body.scene.get(i).getId());
                                    homeBean.setAreacode(body.scene.get(i).getScene_sort_id());
                                    homeBean.setPic(body.scene.get(i).getPicture());
                                    homeBeanList.add(homeBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            HomeAdapter homeAdapter = new HomeAdapter(homeBeanList);
                            recyclerView.setAdapter(homeAdapter); //dialog.show
                            LinearLayoutManager layoutManager = new LinearLayoutManager(KindActivity.this);
                            recyclerView.setLayoutManager(layoutManager);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(KindActivity.this, "请确认登录情况", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<KindBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
                        Toast.makeText(KindActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
