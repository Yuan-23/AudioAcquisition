package com.example.audioacquisition.Mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.audioacquisition.Core.adapter.HomeAdapter;
import com.example.audioacquisition.Mine.adapter.StudyAdapter;
import com.example.audioacquisition.Mine.bean.Study;
import com.example.audioacquisition.R;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {
    TextView SingelSort;
    TextView UnitSort;
    RecyclerView StudyRv;
    List<Study> studyList = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        SingelSort = (TextView) findViewById(R.id.study_singel_sort);
        UnitSort = (TextView) findViewById(R.id.study_unit_sort);
        StudyRv = (RecyclerView) findViewById(R.id.study_rv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });

        initdata();
        StudyAdapter studyAdapter = new StudyAdapter(studyList);
        StudyRv.setAdapter(studyAdapter); //dialog.show
        LinearLayoutManager layoutManager = new LinearLayoutManager(StudyActivity.this);
        StudyRv.setLayoutManager(layoutManager);

    }

    public void initdata() {
        Study study1 = new Study("第1次全省警员执法抽查模拟考试", "99");
        studyList.add(study1);
        Study study2 = new Study("第2次全省警员执法抽查模拟考试", "98");
        studyList.add(study2);

        Study study3 = new Study("第3次全省警员执法抽查模拟考试", "95");
        studyList.add(study3);

        Study study4 = new Study("第4次全省警员执法抽查模拟考试", "65");
        studyList.add(study4);

        Study study5 = new Study("第5次全省警员执法抽查模拟考试", "55");
        studyList.add(study5);


    }
}
