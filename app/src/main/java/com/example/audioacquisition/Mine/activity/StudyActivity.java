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
import com.example.audioacquisition.Mine.adapter.StudyAdapter;
import com.example.audioacquisition.Mine.bean.Study;
import com.example.audioacquisition.Mine.passbean.PracticeBean;
import com.example.audioacquisition.Mine.passbean.StudyBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {
    RecyclerView StudyRv;
    List<Study> studyList = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        StudyRv = (RecyclerView) findViewById(R.id.study_rv);
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
        System.out.println("考试列表");
        OkGo.<StudyBean>post(UrlConstants.MyTest)
                .params("user_id", SharedPreferencesHelper.getUserId(StudyActivity.this))
                .execute(new GsonCallback<StudyBean>(StudyBean.class) {
                    @Override
                    public void onSuccess(Response<StudyBean> response) {
                        StudyBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                for (int i = 0; i < body.exams.size(); i++) {
                                    Study study = new Study();
                                    study.setExamid(body.exams.get(i).getId());
                                    study.setName(body.exams.get(i).getExam_name());
                                    study.setScore(body.exams.get(i).getScore() +"");
                                    studyList.add(study);
                                }

                                StudyAdapter studyAdapter = new StudyAdapter(studyList);
                                StudyRv.setAdapter(studyAdapter); //dialog.show
                                LinearLayoutManager layoutManager = new LinearLayoutManager(StudyActivity.this);
                                StudyRv.setLayoutManager(layoutManager);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(StudyActivity.this, "信息有误，请重试~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<StudyBean> response) {
                        super.onError(response);
                        System.out.println("测试1：网络失败");
                        Toast.makeText(StudyActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
