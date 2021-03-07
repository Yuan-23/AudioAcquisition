package com.example.audioacquisition.Study.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.R;
import com.example.audioacquisition.Study.adapter.LegalAdapter;
import com.example.audioacquisition.Study.bean.LegalBean;
import com.example.audioacquisition.Study.passbean.PolicyPassBean;
import com.example.audioacquisition.Study.passbean.StudynewDetailBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class PolicyActivity extends AppCompatActivity {
    RecyclerView policyrv;
    List<LegalBean> legalBeanList = new ArrayList<>();
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });
        policyrv = (RecyclerView) findViewById(R.id.policy_rv);

        initData();

    }

    public void initData() {
        OkGo.<PolicyPassBean>post(UrlConstants.Policy)
                .params("district_id", SharedPreferencesHelper.getUserBean(getApplicationContext()).getDistrict_id())//场景编号
                .execute(new GsonCallback<PolicyPassBean>(PolicyPassBean.class) {
                    @Override
                    public void onSuccess(Response<PolicyPassBean> response) {
                        PolicyPassBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                for (int i = 0; i < body.policyRules.size(); i++) {
                                    LegalBean legalBean = new LegalBean();
                                    legalBean.setLegal(body.policyRules.get(i).getName());
                                    legalBean.setId(body.policyRules.get(i).getId());
                                    legalBeanList.add(legalBean);
                                }
                                LegalAdapter legalAdapter = new LegalAdapter(legalBeanList);
                                policyrv.setAdapter(legalAdapter); //dialog.show
                                LinearLayoutManager layoutManager = new LinearLayoutManager(PolicyActivity.this);
                                policyrv.setLayoutManager(layoutManager);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getApplicationContext(), "加载失败~", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<PolicyPassBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
