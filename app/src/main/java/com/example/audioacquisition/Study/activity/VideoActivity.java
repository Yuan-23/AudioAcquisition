package com.example.audioacquisition.Study.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Practice.activity.FullActivity;
import com.example.audioacquisition.Study.adapter.ContentAdapter;
import com.example.audioacquisition.Study.adapter.LegalAdapter;
import com.example.audioacquisition.Study.adapter.OtherAdapter;
import com.example.audioacquisition.Study.bean.LegalBean;
import com.example.audioacquisition.Study.bean.OtherBean;
import com.example.audioacquisition.Study.passbean.TeachPassBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    VideoView vv;
    RecyclerView contentRv;
    RecyclerView legalRv;
    ImageView videofull;
    String VideoUrl = null;

    private List<LegalBean> legalBeanArrayList1 = new ArrayList<>();
    private List<LegalBean> legalBeanArrayList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        vv = (VideoView) findViewById(R.id.video_vv);
        videofull = (ImageView) findViewById(R.id.video_full);
        contentRv = (RecyclerView) findViewById(R.id.video_content_rv);
        legalRv = (RecyclerView) findViewById(R.id.video_legal_rv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });

        videofull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FullActivity.class);
                intent.putExtra("full", VideoUrl);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int SceneId = intent.getIntExtra("sceneId", -1);
        init(SceneId);

    }

    public void init(int scenecode) {
        System.out.println("输出来ID=" + scenecode);
        System.out.println("USERID=" + SharedPreferencesHelper.getUserId(getApplicationContext()));

        OkGo.<TeachPassBean>post(UrlConstants.TeachVideo)
                .params("scene_id", scenecode)//类别
                .params("user_id", SharedPreferencesHelper.getUserId(getApplicationContext()))
                .execute(new GsonCallback<TeachPassBean>(TeachPassBean.class) {
                    @Override
                    public void onSuccess(Response<TeachPassBean> response) {
                        TeachPassBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                //视频
                                Uri uri = Uri.parse(body.scene.getTeacher_url());
                                VideoUrl = body.scene.getTeacher_url();
                                vv.setVideoURI(uri);
                                //创建MediaController对象
                                MediaController mediaController = new MediaController(VideoActivity.this);
                                //VideoView与MediaController建立关联
                                vv.setMediaController(mediaController);
                                //让VideoView获取焦点
                                vv.requestFocus();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                for (int i = 0; i < body.scene.getLegalBases().size(); i++) {
                                    LegalBean legalBean = new LegalBean();
                                    legalBean.setLegal(body.scene.getLegalBases().get(i).getContent());
                                    legalBeanArrayList1.add(legalBean);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            LegalAdapter legalAdapter = new LegalAdapter(legalBeanArrayList1);
                            legalRv.setAdapter(legalAdapter); //dialog.show
                            LinearLayoutManager layoutManager = new LinearLayoutManager(VideoActivity.this);
                            legalRv.setLayoutManager(layoutManager);

                            try {
                                for (int i = 0; i < body.scene.getEssentials().size(); i++) {
                                    LegalBean legalBean = new LegalBean();
                                    legalBean.setLegal(body.scene.getEssentials().get(i).getContent());
                                    legalBeanArrayList2.add(legalBean);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            ContentAdapter contentAdapter = new ContentAdapter(legalBeanArrayList2);
                            contentRv.setAdapter(contentAdapter); //dialog.show
                            LinearLayoutManager layoutManager2 = new LinearLayoutManager(VideoActivity.this);
                            contentRv.setLayoutManager(layoutManager2);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(VideoActivity.this, "请确认登录情况", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<TeachPassBean> response) {
                        super.onError(response);
                        System.out.println("视频4：网络失败");
                        Toast.makeText(VideoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
