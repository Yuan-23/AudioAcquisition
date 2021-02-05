package com.example.audioacquisition.Practice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.audioacquisition.Core.data.MyDialog;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Home.passbean.TotalVideoBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class TestActivity extends AppCompatActivity {

    Toolbar toolbar;


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


      //  init();


    }


    public void init() {
        OkGo.<TotalVideoBean>post(UrlConstants.TotalTestVideo)
                .params("scene_id", SharedPreferencesHelper.getSceneCode(TestActivity.this))//场景编号
                .execute(new GsonCallback<TotalVideoBean>(TotalVideoBean.class) {
                    @Override
                    public void onSuccess(Response<TotalVideoBean> response) {
                        TotalVideoBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
//                                String videoUrl2 = body.scene.getVideo_url();
//                                Uri uri = Uri.parse(videoUrl2);
//                                mVideoView.setVideoURI(uri);
//                                //创建MediaController对象
//                                MediaController mediaController = new MediaController(TestActivity.this);
//                                //VideoView与MediaController建立关联
//                                mVideoView.setMediaController(mediaController);
//
//                                //让VideoView获取焦点
//                                mVideoView.requestFocus();
//

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(TestActivity.this, "视频加载失败~", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<TotalVideoBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
                        Toast.makeText(TestActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
