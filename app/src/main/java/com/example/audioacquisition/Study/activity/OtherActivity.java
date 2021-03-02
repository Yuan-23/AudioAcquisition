package com.example.audioacquisition.Study.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.audioacquisition.Core.data.KindPermissions;
import com.example.audioacquisition.Study.adapter.OtherAdapter;
import com.example.audioacquisition.Study.bean.OtherBean;
import com.example.audioacquisition.R;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<OtherBean> otherBeanList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        recyclerView = (RecyclerView) findViewById(R.id.other_rv);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });

        if (Build.VERSION.SDK_INT >= 23) {
            KindPermissions.RequestAudioPermissions(this);
            KindPermissions.RequestVedioPermissions(this);
        }


        OtherBean otherBean1 = new OtherBean(null, "该视频由XXX警官在XX地执行XXXX公务时所录制，该视频XXX XXX", "XXX公安局");
        otherBeanList.add(otherBean1);
        OtherBean otherBean2 = new OtherBean(null, "该视频由XXX警官在XX地执行XXXX公务时所录制，该视频XXX XXX", "XXX公安局");
        otherBeanList.add(otherBean2);

        OtherAdapter otherAdapter = new OtherAdapter(otherBeanList);
        recyclerView.setAdapter(otherAdapter); //dialog.show
        GridLayoutManager layoutManager = new GridLayoutManager(OtherActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);

    }

    //   public void init() {


//        OkGo.<TotalVideoBean>post(UrlConstants.Other)
//                .params("scene_id", SharedPreferencesHelper.getSceneCode(OtherActivity.this))//场景编号
//                .execute(new GsonCallback<TotalVideoBean>(TotalVideoBean.class) {
//                    @Override
//                    public void onSuccess(Response<TotalVideoBean> response) {
//                        TotalVideoBean body = response.body();
//                        if (body.status.equals("200")) {
//                            try {
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        } else if (body.status.equals("500")) {
//                            Toast.makeText(OtherActivity.this, "视频加载失败~", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<TotalVideoBean> response) {
//                        super.onError(response);
//                        System.out.println("登录测试4：网络失败");
//                        Toast.makeText(OtherActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


}
