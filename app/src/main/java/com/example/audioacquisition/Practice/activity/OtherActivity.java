package com.example.audioacquisition.Practice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.audioacquisition.Core.data.KindPermissions;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Home.passbean.TotalVideoBean;
import com.example.audioacquisition.Practice.adapter.OtherAdapter;
import com.example.audioacquisition.Practice.adapter.SecurityAdapter;
import com.example.audioacquisition.Practice.bean.OtherBean;
import com.example.audioacquisition.Practice.bean.TrafficBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import static com.example.audioacquisition.Practice.fragment.TrafficFragment.EXTERNAL_STORAGE_REQ_CODE;

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



        OtherBean otherBean1 = new OtherBean("11", "该视频由XXX警官在XX地执行XXXX公务时所录制，该视频XXX XXX", "XXX公安局");
        otherBeanList.add(otherBean1);
        OtherBean otherBean2 = new OtherBean("22", "该视频由XXX警官在XX地执行XXXX公务时所录制，该视频XXX XXX", "XXX公安局");
        otherBeanList.add(otherBean2);
        OtherBean otherBean3 = new OtherBean("33", "该视频由XXX警官在XX地执行XXXX公务时所录制，该视频XXX XXX", "XXX公安局");
        otherBeanList.add(otherBean3);
        OtherBean otherBean4 = new OtherBean("44", "该视频由XXX警官在XX地执行XXXX公务时所录制，该视频XXX XXX", "XXX公安局");
        otherBeanList.add(otherBean4);

        OtherAdapter otherAdapter = new OtherAdapter(otherBeanList);
        recyclerView.setAdapter(otherAdapter); //dialog.show
        LinearLayoutManager layoutManager = new LinearLayoutManager(OtherActivity.this);
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
