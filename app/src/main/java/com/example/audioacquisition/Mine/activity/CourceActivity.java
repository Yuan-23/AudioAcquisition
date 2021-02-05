package com.example.audioacquisition.Mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.adapter.CourceAdapter;
import com.example.audioacquisition.Mine.bean.Cource;
import com.example.audioacquisition.Mine.passbean.CourceBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class CourceActivity extends AppCompatActivity {
    TextView CourceNumber;
    TextView CourceSort;
    RecyclerView CourceRv;
    List<Cource> courceList = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cource);
        CourceNumber = (TextView) findViewById(R.id.cource_number);
        CourceSort = (TextView) findViewById(R.id.cource_sort);
        CourceRv = (RecyclerView) findViewById(R.id.cource_rv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });


        initdata();

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
                                CourceSort.setText(body.rank + "");
                                for (int i = 0; i < body.scene.size(); i++) {//最多访问一页的条数
                                    Cource cource = new Cource();
                                    cource.setCourcename(body.scene.get(i).getScene_name());
                                    cource.setLearnflag(body.scene.get(i).getLearnFlag());
                                    courceList.add(cource);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            CourceAdapter courceAdapter = new CourceAdapter(courceList);
                            CourceRv.setAdapter(courceAdapter); //dialog.show
                            LinearLayoutManager layoutManager = new LinearLayoutManager(CourceActivity.this);
                            CourceRv.setLayoutManager(layoutManager);

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
