package com.example.audioacquisition.Home.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Home.bean.DetailBean;
import com.example.audioacquisition.Home.passbean.GetVoiceBean;
import com.example.audioacquisition.Home.passbean.PassBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MoniAdapter extends RecyclerView.Adapter<MoniAdapter.ViewHolder> {
    private List<DetailBean> detailBeanList;
    private Context context;
    private View mview;


    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder = new MediaRecorder();
    private File audioFile0;
    private File audioFile1;
    private File audioFile2;
    private File audioFile3;
    private File audioFile4;
    private File audioFile5;


    public MoniAdapter(List<DetailBean> detailBeans, Context context) {
        this.context = context;
        this.detailBeanList = detailBeans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mBtnRecording;// 录音按钮
        private VideoView mvideo;//分段视频
        int scenecode;
        String stage;
        String number;
        int sort;

        private LinearLayout mllRecording;
        private LinearLayout mllStop;

        private ViewHolder(View view) {
            super(view);
            mBtnRecording = (TextView) view.findViewById(R.id.moni_start_tx);
            mllRecording = (LinearLayout) view.findViewById(R.id.moni_start_ll);
            mllStop = (LinearLayout) view.findViewById(R.id.moni_stop_ll);
            mvideo = (VideoView) view.findViewById(R.id.item_moni_vv);
        }

    }

    public MoniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_moni, parent, false);
        mview = view;
        MoniAdapter.ViewHolder dholder = new MoniAdapter.ViewHolder(view);
        return dholder;

    }

    public void onBindViewHolder(final MoniAdapter.ViewHolder holder, int position) {
        final DetailBean detailBean = detailBeanList.get(position);
        holder.scenecode = detailBean.getScenecode();
        holder.number = detailBean.getNumber();
        holder.stage = detailBean.getStage();
        holder.sort = detailBean.getSort();
        SharedPreferencesHelper.setRecordStatus(0, context);//录音状态置0——未录音

        //网络视频
        String videoUrl = detailBean.getContext();
        Uri uri = Uri.parse(videoUrl);
        holder.mvideo.setVideoURI(uri);
        //创建MediaController对象
        MediaController mediaController = new MediaController(context);
        //VideoView与MediaController建立关联
        holder.mvideo.setMediaController(mediaController);
        //让VideoView获取焦点
        holder.mvideo.requestFocus();


        holder.mllRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setEnabled为false，该控件将不再响应点击、触摸以及键盘事件等，处于完全被禁用的状态，并且该控件会被重绘。
                // 对于Button来说，设置为false，控件会变灰不可点击。
                // 对于EditText来说，控件也会变灰，且不可输入文字。
                if ((holder.mBtnRecording.getText().equals("录音中")) || (SharedPreferencesHelper.getRecordStatus(context) == 1)) {
                    Toast.makeText(context, "正在录音中哦~", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferencesHelper.setRecordStatus(1, context);//录音状态置1——正在录音
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//音频输入源（获得音频资源：defalut，camcorder，mic，voice_call，voice_communication,voice_downlink, voice_recognition,  voice_uplink）
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT); //设置输出格式(default，AAC，AMR_NB，AMR_WB)
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); //设置声音编码类型（default，AAC，AMR_NB，AMR_WB,）
                    try {
                        switch (holder.sort) {
                            case 0:
                                audioFile0 = File.createTempFile("record00_", ".wav");
                                mediaRecorder.setOutputFile(audioFile0.getAbsolutePath());
                                break;
                            case 1:
                                audioFile1 = File.createTempFile("record11_", ".wav");
                                mediaRecorder.setOutputFile(audioFile1.getAbsolutePath());
                                break;
                            case 2:
                                audioFile2 = File.createTempFile("record22_", ".wav");
                                mediaRecorder.setOutputFile(audioFile2.getAbsolutePath());
                                break;
                            case 3:
                                audioFile3 = File.createTempFile("record33_", ".wav");
                                mediaRecorder.setOutputFile(audioFile3.getAbsolutePath());
                                break;
                            case 4:
                                audioFile4 = File.createTempFile("record44_", ".wav");
                                mediaRecorder.setOutputFile(audioFile4.getAbsolutePath());
                                break;
                            case 5:
                                audioFile5 = File.createTempFile("record55_", ".wav");
                                mediaRecorder.setOutputFile(audioFile5.getAbsolutePath());
                                break;
                            default:
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        mediaRecorder.prepare();//准备录制
                        mediaRecorder.start(); //开始录制
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    holder.mBtnRecording.setText("录音中");
                }
            }
        });

        holder.mllStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferencesHelper.getRecordStatus(context) == 0) {
                    Toast.makeText(context, "暂无录音文件", Toast.LENGTH_SHORT).show();
                } else {
                    holder.mBtnRecording.setText("录音");
                    File audioFile = null;
                    switch (holder.sort) {
                        case 0:
                            audioFile = audioFile0;
                            break;
                        case 1:
                            audioFile = audioFile1;
                            break;
                        case 2:
                            audioFile = audioFile2;
                            break;
                        case 3:
                            audioFile = audioFile3;
                            break;
                        case 4:
                            audioFile = audioFile4;
                            break;
                        case 5:
                            audioFile = audioFile5;
                            break;
                        default:
                            break;

                    }
                    if (audioFile == null) {
                        Toast.makeText(context, "暂无录音文件", Toast.LENGTH_SHORT).show();
                    } else { //完成按钮
                        OkGo.<PassBean>post(UrlConstants.PassVideo)
                                .params("scene_id", holder.scenecode)//场景编号
                                .params("user_id", SharedPreferencesHelper.getUserId(context))//警察编号
                                .params("stage", holder.stage)//阶段内容
                                .params("number", holder.number)//阶段中的第几段
                                .params("voice", audioFile)//MultipartFile
                                .params("type", 0)//类型，0为看文字说，1为看视频说
                                .execute(new GsonCallback<PassBean>(PassBean.class) {
                                    @Override
                                    public void onSuccess(Response<PassBean> response) {
                                        PassBean body = response.body();
                                        if (body.status.equals("200")) {
                                            Toast.makeText(context, "语音已上传", Toast.LENGTH_SHORT).show();
                                            SharedPreferencesHelper.setRecordStatus(0, context);//录音状态置0——未录音
                                        } else if (body.status.equals("500")) {
                                            Toast.makeText(context, "上传失败，请重新录音哦", Toast.LENGTH_SHORT).show();
                                            SharedPreferencesHelper.setRecordStatus(0, context);//录音状态置0——未录音
                                        }

                                    }

                                    @Override
                                    public void onError(Response<PassBean> response) {
                                        super.onError(response);
                                        System.out.println("登录测试4：网络失败");
                                        Toast.makeText(context, "网络或服务端异常，请求数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        mediaRecorder.stop();//先停止
                        mediaRecorder.reset();// 在重置mediarecorder
                        //加了就不能重复录音
//                    mediaRecorder.release();//释放mediarecorder
//                   mediaRecorder = null;
                    }
                }
            }
        });

    }

    public int getItemCount() {
        return detailBeanList.size();
    }

}
