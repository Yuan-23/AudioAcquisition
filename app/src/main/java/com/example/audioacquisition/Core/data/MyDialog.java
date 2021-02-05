package com.example.audioacquisition.Core.data;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Home.passbean.PassBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;

public class MyDialog extends Dialog {
    private Context mContext;
    private LinearLayout recordll;
    private LinearLayout stopll;
    private MediaRecorder mediaRecorder = new MediaRecorder();
    private File testFile;
    private TextView recordtv;
    private String message;
    private int recordflag = 0;//还未录音

    private MyOnclickListener mMyOnclickListener;

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item);
        setCanceledOnTouchOutside(false);
        initView();
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = 1040;
        layoutParams.height = 1200;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.x = 100;
        layoutParams.y = 100;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    private void initView() {
        recordll = (LinearLayout) findViewById(R.id.dialog_start_ll);
        stopll = (LinearLayout) findViewById(R.id.dialog_stop_ll);

        recordtv = (TextView) findViewById(R.id.dialog_start_tx);

//        buttonConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("测试自定义dialog的按钮点击");
//                mMyOnclickListener.onYesClick(message);
//            }
//        });

        recordll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recordflag == 1) {
                    Toast.makeText(getContext(), "正在录音中哦~", Toast.LENGTH_SHORT).show();
                } else {
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//音频输入源（获得音频资源：defalut，camcorder，mic，voice_call，voice_communication,voice_downlink, voice_recognition,  voice_uplink）
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT); //设置输出格式(default，AAC，AMR_NB，AMR_WB)
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); //设置声音编码类型（default，AAC，AMR_NB，AMR_WB,）

                    try {
                        testFile = File.createTempFile("test_", ".wav");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaRecorder.setOutputFile(testFile.getAbsolutePath());
                    try {
                        mediaRecorder.prepare();//准备录制
                        mediaRecorder.start(); //开始录制
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recordflag = 1;
                    recordtv.setText("录音中");
                }


            }
        });

        stopll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((recordflag == 0) || (testFile == null)) {
                    Toast.makeText(getContext(), "暂无录音文件", Toast.LENGTH_SHORT).show();
                } else {
                    recordtv.setText("录音");
                    OkGo.<PassBean>post(UrlConstants.PassVideo)
                            .params("scene_code", SharedPreferencesHelper.getSceneCode(getContext()))//场景编号
                            .params("police_number", SharedPreferencesHelper.getUserAccount(getContext()))//警察编号
//                            .params("stage", holder.stage)//阶段内容
//                            .params("number", holder.number)//阶段中的第几段
                            .params("voice", testFile)//MultipartFile
                            .execute(new GsonCallback<PassBean>(PassBean.class) {
                                @Override
                                public void onSuccess(Response<PassBean> response) {
                                    PassBean body = response.body();
                                    if (body.status.equals("200")) {
                                        Toast.makeText(getContext(), "语音已上传", Toast.LENGTH_SHORT).show();
                                        mediaRecorder.stop();//先停止
                                        mediaRecorder.reset();// 在重置mediarecorder
                                        dismiss();//上传成功则消失
                                    } else if (body.status.equals("500")) {
                                        recordflag = 0;
                                        Toast.makeText(getContext(), "上传失败，请重新录音哦", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Response<PassBean> response) {
                                    super.onError(response);
                                    System.out.println("登录测试4：网络失败");
                                    Toast.makeText(getContext(), "网络或服务端异常，请求数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });

    }

    //内部接口
    public interface MyOnclickListener {
        public void onYesClick(String message);
    }

    //set内部接口和String参数
    public void setMyOnclickListener(String message, MyOnclickListener myOnclickListener) {
        this.message = message;
        this.mMyOnclickListener = myOnclickListener;
    }
}