package com.example.audioacquisition.Practice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.audioacquisition.Core.bean.KindBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Practice.adapter.TrafficAdapter;
import com.example.audioacquisition.Practice.bean.TrafficBean;
import com.example.audioacquisition.Practice.passbean.TeachPassBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class VideoActivity extends AppCompatActivity {
    int SceneSortId;
    VideoView vv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        vv = (VideoView) findViewById(R.id.video_vv);
        tv = (TextView) findViewById(R.id.video_tv);

        Intent intent = getIntent();
        SceneSortId = intent.getIntExtra("sceneSortId", -1);

        init();

    }

    public void init() {
        OkGo.<TeachPassBean>post(UrlConstants.TeachVideo)
                .params("scene_id", SceneSortId)//类别
                .params("user_id", SharedPreferencesHelper.getUserBean(VideoActivity.this).getId())
                .execute(new GsonCallback<TeachPassBean>(TeachPassBean.class) {
                    @Override
                    public void onSuccess(Response<TeachPassBean> response) {
                        TeachPassBean body = response.body();
                        if (body.getStatus().equals("200")) {
                            tv.setText(body.getScene().getLegalBases().get(0).getContent());

                            //网络视频
                            String videoUrl = body.getScene().getVideo_url();
                            Uri uri = Uri.parse(videoUrl);
                            vv.setVideoURI(uri);
                            //创建MediaController对象
                            MediaController mediaController = new MediaController(VideoActivity.this);
                            //VideoView与MediaController建立关联
                            vv.setMediaController(mediaController);
                            //让VideoView获取焦点
                            vv.requestFocus();

                        } else if (body.getStatus().equals("500")) {
                            Toast.makeText(VideoActivity.this, "请确认登录情况", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<TeachPassBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
                        Toast.makeText(VideoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
