package com.example.audioacquisition.Practice.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Practice.bean.DetailBean;
import com.example.audioacquisition.Practice.passbean.GetVoiceBean;
import com.example.audioacquisition.Practice.passbean.PassBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;
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
    private File audioFile6;
    private File audioFile7;
    private File audioFile8;
    private File audioFile9;;


    public DetailAdapter(List<DetailBean> detailBeans, Context context) {
        this.context = context;
        this.detailBeanList = detailBeans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mcontext;
        TextView mBtnRecording;// 录音按钮
        private TextView mBtnPlay;// 播放按钮
        int scenecode;
        String stage;
        String number;
        int sort;

        LinearLayout mllRecording;
        private LinearLayout mllPlay;
        LinearLayout mllStop;

        private ViewHolder(View view) {
            super(view);
            mcontext = (TextView) view.findViewById(R.id.item_context);
            mBtnPlay = (TextView) view.findViewById(R.id.item_trying_tx);
            mBtnRecording = (TextView) view.findViewById(R.id.item_start_tx);
            mllPlay = (LinearLayout) view.findViewById(R.id.item_trying_ll);
            mllRecording = (LinearLayout) view.findViewById(R.id.item_start_ll);
            mllStop = (LinearLayout) view.findViewById(R.id.item_stop_ll);
        }

    }

    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail, parent, false);
        mview = view;
        DetailAdapter.ViewHolder dholder = new DetailAdapter.ViewHolder(view);
        return dholder;

    }

    public void onBindViewHolder(final DetailAdapter.ViewHolder holder, int position) {
        final DetailBean detailBean = detailBeanList.get(position);
        holder.mcontext.setText(detailBean.getContext());
        holder.scenecode = detailBean.getScenecode();
        holder.number = detailBean.getNumber();
        holder.stage = detailBean.getStage();
        holder.sort = detailBean.getSort();
        SharedPreferencesHelper.setRecordStatus(0, context);//录音状态置0——未录音
        SharedPreferencesHelper.setPlayStatus(0, context);//播放状态置0——未播放


        holder.mllPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferencesHelper.getRecordStatus(context) == 1) {//holder.mBtnRecording.getText().equals("录音中")
                    Toast.makeText(context, "正在录音中哦~", Toast.LENGTH_SHORT).show();
                } else if (SharedPreferencesHelper.getPlayStatus(context) == 1) {
                    Toast.makeText(context, "正在播放录音中哦~", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer = new MediaPlayer();
                    OkGo.<GetVoiceBean>post(UrlConstants.GetVideo)
                            .params("user_id", SharedPreferencesHelper.getUserId(context))//用户id
                            .params("scene_id", holder.scenecode)//场景编号
                            .params("stage", holder.stage)//阶段内容
                            .params("number", holder.number)//阶段中的第几段
                            .params("type", 0)//类型，0为看文字说，1为看视频说返回参数
                            .execute(new GsonCallback<GetVoiceBean>(GetVoiceBean.class) {
                                @Override
                                public void onSuccess(Response<GetVoiceBean> response) {
                                    GetVoiceBean body = response.body();
                                    if (body.status.equals("200")) {
                                        Uri uri = Uri.parse(body.videoUrl);
                                        try {
                                            SharedPreferencesHelper.setPlayStatus(1, context);
                                            mediaPlayer.setDataSource(context, uri);
                                            mediaPlayer.prepare();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        if (mediaPlayer.isPlaying()) {
                                            mediaPlayer.stop();
                                            holder.mBtnPlay.setText("播放");
                                            mediaPlayer.release();
                                            mediaPlayer.setLooping(false);
                                            //mediaPlayer = MediaPlayer.create(this, R.raw.dd);
                                        }
                                        holder.mBtnPlay.setText("播放中");
                                        mediaPlayer.start();

                                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                mp.start();
                                                holder.mBtnPlay.setText("播放");
                                                Toast.makeText(context, "录音播放完毕.", Toast.LENGTH_SHORT).show();
                                                mp.stop();
                                                SharedPreferencesHelper.setPlayStatus(0, context);

                                            }
                                        });


                                    } else if (body.status.equals("500")) {
                                        SharedPreferencesHelper.setPlayStatus(0, context);
                                        Toast.makeText(context, "还未进行过该项录音哦", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Response<GetVoiceBean> response) {
                                    super.onError(response);
                                    System.out.println("登录测试4：网络失败");
                                    Toast.makeText(context, "网络或服务端异常，请求数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        holder.mllRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setEnabled为false，该控件将不再响应点击、触摸以及键盘事件等，处于完全被禁用的状态，并且该控件会被重绘。
                // 对于Button来说，设置为false，控件会变灰不可点击。
                // 对于EditText来说，控件也会变灰，且不可输入文字。
                if ((holder.mBtnRecording.getText().equals("录音中")) || (SharedPreferencesHelper.getRecordStatus(context) == 1)) {
                    Toast.makeText(context, "正在录音中哦~", Toast.LENGTH_SHORT).show();
                } else if (SharedPreferencesHelper.getPlayStatus(context) == 1) {
                    Toast.makeText(context, "正在播放录音中哦~", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferencesHelper.setRecordStatus(1, context);//录音状态置1——正在录音
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//音频输入源（获得音频资源：defalut，camcorder，mic，voice_call，voice_communication,voice_downlink, voice_recognition,  voice_uplink）
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT); //设置输出格式(default，AAC，AMR_NB，AMR_WB)
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); //设置声音编码类型（default，AAC，AMR_NB，AMR_WB,）
                    try {
                        switch (holder.sort) {
                            case 0:
                                audioFile0 = File.createTempFile("record0_", ".wav");
                                mediaRecorder.setOutputFile(audioFile0.getAbsolutePath());
                                break;
                            case 1:
                                audioFile1 = File.createTempFile("record1_", ".wav");
                                mediaRecorder.setOutputFile(audioFile1.getAbsolutePath());
                                break;
                            case 2:
                                audioFile2 = File.createTempFile("record2_", ".wav");
                                mediaRecorder.setOutputFile(audioFile2.getAbsolutePath());
                                break;
                            case 3:
                                audioFile3 = File.createTempFile("record3_", ".wav");
                                mediaRecorder.setOutputFile(audioFile3.getAbsolutePath());
                                break;
                            case 4:
                                audioFile4 = File.createTempFile("record4_", ".wav");
                                mediaRecorder.setOutputFile(audioFile4.getAbsolutePath());
                                break;
                            case 5:
                                audioFile5 = File.createTempFile("record5_", ".wav");
                                mediaRecorder.setOutputFile(audioFile5.getAbsolutePath());
                                break;
                            case 6:
                                audioFile6 = File.createTempFile("record6_", ".wav");
                                mediaRecorder.setOutputFile(audioFile6.getAbsolutePath());
                                break;
                            case 7:
                                audioFile7 = File.createTempFile("record7_", ".wav");
                                mediaRecorder.setOutputFile(audioFile7.getAbsolutePath());
                                break;
                            case 8:
                                audioFile8 = File.createTempFile("record8_", ".wav");
                                mediaRecorder.setOutputFile(audioFile8.getAbsolutePath());
                                break;
                            case 9:
                                audioFile9 = File.createTempFile("record9_", ".wav");
                                mediaRecorder.setOutputFile(audioFile9.getAbsolutePath());
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
                if (SharedPreferencesHelper.getPlayStatus(context) == 1) {
                    Toast.makeText(context, "正在播放录音中哦~", Toast.LENGTH_SHORT).show();
                } else if (SharedPreferencesHelper.getRecordStatus(context) == 0) {
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
                        case 6:
                            audioFile = audioFile6;
                            break;
                        case 7:
                            audioFile = audioFile7;
                            break;
                        case 8:
                            audioFile = audioFile8;
                            break;
                        case 9:
                            audioFile = audioFile9;
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
