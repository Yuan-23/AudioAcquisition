package com.example.audioacquisition.Test.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.audioacquisition.R;

public class TestActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tcertain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });
        tcertain = (TextView) findViewById(R.id.test_certain);

        Intent intent = getIntent();
        int scene = intent.getIntExtra("sceneid", -1);


        tcertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, ExamActivity.class);
                intent.putExtra("sceneId", scene);
                startActivity(intent);
                finish();
            }
        });


    }


}
