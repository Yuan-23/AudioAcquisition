package com.example.audioacquisition.Mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.example.audioacquisition.Mine.passbean.PracticeBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class PracticeActivity extends AppCompatActivity {
    TextView solonum;
    TextView soloscore;
    TextView soloscoresort;
    TextView solonumsort;
    TextView totalnum;
    TextView totalscore;
    TextView totalnumsort;
    TextView totalscoresort;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        solonum = (TextView) findViewById(R.id.practice_solo_num);
        soloscore = (TextView) findViewById(R.id.practice_solo_score);
        totalnum = (TextView) findViewById(R.id.practice_total_num);
        totalscore = (TextView) findViewById(R.id.practice_total_score);
        solonumsort = (TextView) findViewById(R.id.practice_solo_numsort);
        soloscoresort = (TextView) findViewById(R.id.practice_solo_scoresort);
        totalnumsort = (TextView) findViewById(R.id.practice_total_numsort);
        totalscoresort = (TextView) findViewById(R.id.practice_total_scoresort);

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

        OkGo.<PracticeBean>post(UrlConstants.Practice)
                .params("user_id", SharedPreferencesHelper.getUserId(PracticeActivity.this))
                .execute(new GsonCallback<PracticeBean>(PracticeBean.class) {
                    @Override
                    public void onSuccess(Response<PracticeBean> response) {
                        PracticeBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                solonum.setText(body.userNum + " 次");
                                soloscore.setText(body.userScore + " 分");
                                solonumsort.setText(body.userNumRank + " 名");
                                soloscoresort.setText(body.userRank + " 名");
                                totalnum.setText(body.departNum + " 次");
                                totalscore.setText(body.departScore + " 分");
                                totalnumsort.setText(body.departNumRank + " 名");
                                totalscoresort.setText(body.departRank + " 名");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(PracticeActivity.this, "暂无训练信息~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<PracticeBean> response) {
                        super.onError(response);
                        System.out.println("测试1：网络失败");
                        Toast.makeText(PracticeActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
