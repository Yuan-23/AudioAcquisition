package com.example.audioacquisition.Practice.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Practice.activity.FullActivity;
import com.example.audioacquisition.Practice.adapter.DetailAdapter;
import com.example.audioacquisition.Practice.bean.DetailBean;
import com.example.audioacquisition.Practice.passbean.DetailPassBean;
import com.example.audioacquisition.Practice.passbean.ScoreBean;
import com.example.audioacquisition.Practice.passbean.TotalVideoBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;


public class HorizontalFragment extends Fragment {
    private String VideoUrl = null;
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;
    private LinearLayout stagell1;
    private TextView stagename1;
    private ImageView stageimage1;
    private RecyclerView stagerv1;
    private LinearLayout stagell2;
    private TextView stagename2;
    private ImageView stageimage2;
    private RecyclerView stagerv2;
    private LinearLayout stagell3;
    private TextView stagename3;
    private ImageView stageimage3;
    private RecyclerView stagerv3;
    private LinearLayout stagell4;
    private TextView stagename4;
    private ImageView stageimage4;
    private RecyclerView stagerv4;

    private int count = 0;//1
    private int mcount = 0;//2

    private Button scoreBtn;
    private TextView scoretotal;
    private TextView firstscore;
    private TextView secondscore;
    private TextView thirdscore;
    private TextView fourthscore;


    private Button mscoreBtn;
    private TextView mscoretotal;
    private VideoView mvideoView;
    private ImageView mfull;

    //在fragment内部写一个静态方法,返回自己;供外部调用;
    public static Fragment getInstance(String title, int scenecode, int areacode) {
        HorizontalFragment mFragment = new HorizontalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("code", scenecode);
        bundle.putInt("area", areacode);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;


        int SceneCode = SharedPreferencesHelper.getSceneCode(getContext());


        Bundle bundle = getArguments();
        // 根据标题是否相同请求不同的接口;
        if (bundle.getString("title").equals("语音收集")) {

            view = inflater.inflate(R.layout.fragment_horizontal, container, false);

            scoreBtn = (Button) view.findViewById(R.id.ifscore_btn);
            scoretotal = (TextView) view.findViewById(R.id.score_total);
            firstscore = (TextView) view.findViewById(R.id.first_score);
            secondscore = (TextView) view.findViewById(R.id.second_score);
            thirdscore = (TextView) view.findViewById(R.id.third_score);
            fourthscore = (TextView) view.findViewById(R.id.fourth_score);

            stagerv1 = (RecyclerView) view.findViewById(R.id.first_rv1);
            stageimage1 = (ImageView) view.findViewById(R.id.first_image);
            stagell1 = (LinearLayout) view.findViewById(R.id.first_ll);
            stagename1 = (TextView) view.findViewById(R.id.first_name);

            stagerv2 = (RecyclerView) view.findViewById(R.id.second_rv2);
            stageimage2 = (ImageView) view.findViewById(R.id.second_image);
            stagell2 = (LinearLayout) view.findViewById(R.id.second_ll);
            stagename2 = (TextView) view.findViewById(R.id.second_name);

            stagerv3 = (RecyclerView) view.findViewById(R.id.third_rv3);
            stageimage3 = (ImageView) view.findViewById(R.id.third_image);
            stagell3 = (LinearLayout) view.findViewById(R.id.third_ll);
            stagename3 = (TextView) view.findViewById(R.id.third_name);

            stagerv4 = (RecyclerView) view.findViewById(R.id.fourth_rv4);
            stageimage4 = (ImageView) view.findViewById(R.id.fourth_image);
            stagell4 = (LinearLayout) view.findViewById(R.id.fourth_ll);
            stagename4 = (TextView) view.findViewById(R.id.fourth_name);

            scoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClickScore(SceneCode);
                }
            });
            System.out.println("是练习");
            initdetail1(SceneCode);//传参传一个场景编号
            initdetail2(SceneCode);
            initdetail3(SceneCode);
            initdetail4(SceneCode);

        } else if (bundle.getString("title").equals("模拟训练")) {

            view = inflater.inflate(R.layout.fragment_second, container, false);

            mscoreBtn = (Button) view.findViewById(R.id.moni_ifscore_btn);
            mscoretotal = (TextView) view.findViewById(R.id.moni_score_total);
            mvideoView = (VideoView) view.findViewById(R.id.moni_vv);
            mfull = (ImageView) view.findViewById(R.id.moni_full);

            mscoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClickScore(SceneCode);
                }
            });

            System.out.println("是模拟");
            Permit();//申请权限

            init(SceneCode);//播放视频

            mfull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), FullActivity.class);
                    intent.putExtra("full", VideoUrl);
                    startActivity(intent);
                }
            });


        }

        return view;
    }

    private void init(int scode) {//播放视频
        OkGo.<TotalVideoBean>post(UrlConstants.TotalTestVideo)
                .params("scene_id", scode)//场景编号
                .execute(new GsonCallback<TotalVideoBean>(TotalVideoBean.class) {
                    @Override
                    public void onSuccess(Response<TotalVideoBean> response) {
                        TotalVideoBean body = response.body();
                        if (body.status.equals("200")) {
                            //网络视频
                            String videoUrl = body.scene.getVideo_url();
                            VideoUrl = videoUrl;//传值
                            Uri uri = Uri.parse(videoUrl);
                            mvideoView.setVideoURI(uri);
                            //创建MediaController对象
                            MediaController mediaController = new MediaController(getContext());
                            //VideoView与MediaController建立关联
                            mvideoView.setMediaController(mediaController);
                            //让VideoView获取焦点
                            mvideoView.requestFocus();

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<TotalVideoBean> response) {
                        super.onError(response);
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void ClickScore(int scenecode) {
        OkGo.<ScoreBean>post(UrlConstants.Score)
                .params("scene_code", scenecode)//场景编号
                .params("police_number", SharedPreferencesHelper.getUserAccount(getContext()))
                .execute(new GsonCallback<ScoreBean>(ScoreBean.class) {
                    @Override
                    public void onSuccess(Response<ScoreBean> response) {
                        ScoreBean body = response.body();
                        if (body.status.equals("200")) {

                            List<String> scorelist;//更新各阶段分数
                            int tempflag = 0;//测试是否有总分
                            scorelist = body.stageScore;

                            for (int i = 0; i < body.scoreFlag.size(); i++) {
                                if (body.scoreFlag.get(i) == 0) {
                                    scorelist.set(i, "计算中");
                                } else {
                                    tempflag = 1;
                                    scorelist.set(i, body.stageScore.get(i) + "分");
                                }
                            }

                            firstscore.setText(scorelist.get(0));
                            secondscore.setText(scorelist.get(1));
                            thirdscore.setText(scorelist.get(2));
                            fourthscore.setText(scorelist.get(3));

                            if (tempflag == 1) {//判断总分是否存在
                                scoretotal.setText(body.score + "分");
                            } else {
                                scoretotal.setText("计算中");
                            }
                            if (body.flag == 1) {
                                Toast.makeText(getContext(), "部分分值正在努力计算中，请稍后重试哦~", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "分值更新成功！", Toast.LENGTH_SHORT).show();
                            }

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "未检测到语音，请录制后重试~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<ScoreBean> response) {
                        super.onError(response);
                        System.out.println("测试分数：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public void Permit() {//申请播放权限

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};//验证是否许可权限
            for (String str : permissions) {
                if (getActivity().checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    getActivity().requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
                //这里就是权限打开之后自己要操作的逻辑
            }
        }

        int permission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) { // 请求权限

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQ_CODE);
        }
    }


//    public void initvideo1(int scode) {
//        System.out.println("scene_id=" + scode);
//
//        List<DetailBean> detailBeanList11 = new ArrayList<>();
//        OkGo.<DetailPassBean>post(UrlConstants.DivideVideo)
//                .params("scene_id", scode)//场景编号
//                .params("stage", 1)//第几阶段
//                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
//                    @Override
//                    public void onSuccess(Response<DetailPassBean> response) {
//                        DetailPassBean body = response.body();
//                        if (body.status.equals("200")) {
//                            mstagename1.setText(body.sceneDetail.getStage_name());
//                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
//                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
//
//                                try {
//                                    DetailBean detailBean = new DetailBean();
//                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getVideo_url());
//                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
//                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
//                                    detailBean.setStage(body.sceneDetail.getStage());
//                                    detailBean.setSort(mcount++);
//                                    detailBeanList11.add(detailBean);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            MoniAdapter moniAdapter1 = new MoniAdapter(detailBeanList11, getActivity());
//                            mstagerv1.setAdapter(moniAdapter1); //dialog.show
//                            LinearLayoutManager layoutManager11 = new LinearLayoutManager(getContext());
//                            mstagerv1.setLayoutManager(layoutManager11);
//
//                        } else if (body.status.equals("500")) {
//                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Response<DetailPassBean> response) {
//                        super.onError(response);
//                        System.out.println("测试1：网络失败");
//                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//    public void initvideo2(int scode) {
//        System.out.println("scene_id=" + scode);
//
//        List<DetailBean> detailBeanList22 = new ArrayList<>();
//        OkGo.<DetailPassBean>post(UrlConstants.DivideVideo)
//                .params("scene_id", scode)//场景编号
//                .params("stage", 2)//第几阶段
//                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
//                    @Override
//                    public void onSuccess(Response<DetailPassBean> response) {
//                        DetailPassBean body = response.body();
//                        if (body.status.equals("200")) {
//                            mstagename2.setText(body.sceneDetail.getStage_name());
//                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
//                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
//
//                                try {
//                                    DetailBean detailBean = new DetailBean();
//                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getVideo_url());
//                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
//                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
//                                    detailBean.setStage(body.sceneDetail.getStage());
//                                    detailBean.setSort(mcount++);
//                                    detailBeanList22.add(detailBean);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            MoniAdapter moniAdapter2 = new MoniAdapter(detailBeanList22, getActivity());
//                            mstagerv2.setAdapter(moniAdapter2); //dialog.show
//                            LinearLayoutManager layoutManager12 = new LinearLayoutManager(getContext());
//                            mstagerv2.setLayoutManager(layoutManager12);
//
//                        } else if (body.status.equals("500")) {
//                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Response<DetailPassBean> response) {
//                        super.onError(response);
//                        System.out.println("测试1：网络失败");
//                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//
//    public void initvideo3(int scode) {
//        System.out.println("scene_id=" + scode);
//
//        List<DetailBean> detailBeanList33 = new ArrayList<>();
//        OkGo.<DetailPassBean>post(UrlConstants.DivideVideo)
//                .params("scene_id", scode)//场景编号
//                .params("stage", 3)//第几阶段
//                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
//                    @Override
//                    public void onSuccess(Response<DetailPassBean> response) {
//                        DetailPassBean body = response.body();
//                        if (body.status.equals("200")) {
//                            mstagename3.setText(body.sceneDetail.getStage_name());
//                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
//                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
//
//                                try {
//                                    DetailBean detailBean = new DetailBean();
//                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getVideo_url());
//                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
//                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
//                                    detailBean.setStage(body.sceneDetail.getStage());
//                                    detailBean.setSort(mcount++);
//                                    detailBeanList33.add(detailBean);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            MoniAdapter moniAdapter3 = new MoniAdapter(detailBeanList33, getActivity());
//                            mstagerv3.setAdapter(moniAdapter3); //dialog.show
//                            LinearLayoutManager layoutManager13 = new LinearLayoutManager(getContext());
//                            mstagerv3.setLayoutManager(layoutManager13);
//
//
//                        } else if (body.status.equals("500")) {
//                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Response<DetailPassBean> response) {
//                        super.onError(response);
//                        System.out.println("测试1：网络失败");
//                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//
//    public void initvideo4(int scode) {
//        System.out.println("scene_id=" + scode);
//
//        List<DetailBean> detailBeanList44 = new ArrayList<>();
//        OkGo.<DetailPassBean>post(UrlConstants.DivideVideo)
//                .params("scene_id", scode)//场景编号
//                .params("stage", 4)//第几阶段
//                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
//                    @Override
//                    public void onSuccess(Response<DetailPassBean> response) {
//                        DetailPassBean body = response.body();
//                        if (body.status.equals("200")) {
//                            mstagename4.setText(body.sceneDetail.getStage_name());
//                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
//                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
//
//                                try {
//                                    DetailBean detailBean = new DetailBean();
//                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getVideo_url());
//                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
//                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
//                                    detailBean.setStage(body.sceneDetail.getStage());
//                                    detailBean.setSort(mcount++);
//                                    detailBeanList44.add(detailBean);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            MoniAdapter moniAdapter4 = new MoniAdapter(detailBeanList44, getActivity());
//                            mstagerv4.setAdapter(moniAdapter4); //dialog.show
//                            LinearLayoutManager layoutManager14 = new LinearLayoutManager(getContext());
//                            mstagerv4.setLayoutManager(layoutManager14);
//
//                        } else if (body.status.equals("500")) {
//                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Response<DetailPassBean> response) {
//                        super.onError(response);
//                        System.out.println("测试1：网络失败");
//                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }


    public void initdetail1(int scode) {
        System.out.println("scene_id=" + scode);

        List<DetailBean> detailBeanList1 = new ArrayList<>();
        OkGo.<DetailPassBean>post(UrlConstants.Detail)
                .params("scene_id", scode)//场景编号
                .params("stage", 1)//第几阶段
                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
                    @Override
                    public void onSuccess(Response<DetailPassBean> response) {
                        DetailPassBean body = response.body();
                        if (body.status.equals("200")) {
                            stagename1.setText(body.sceneDetail.getStage_name());
                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());

                                try {
                                    DetailBean detailBean = new DetailBean();
                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
                                    detailBean.setStage(body.sceneDetail.getStage());
                                    detailBean.setSort(count++);
                                    detailBeanList1.add(detailBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            DetailAdapter detailAdapter1 = new DetailAdapter(detailBeanList1, getActivity());
                            stagerv1.setAdapter(detailAdapter1); //dialog.show
                            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
                            stagerv1.setLayoutManager(layoutManager1);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<DetailPassBean> response) {
                        super.onError(response);
                        System.out.println("测试1：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void initdetail2(int scode) {
        System.out.println("scene_id=" + scode);
        List<DetailBean> detailBeanList2 = new ArrayList<>();
        OkGo.<DetailPassBean>post(UrlConstants.Detail)
                .params("scene_id", scode)//场景编号
                .params("stage", 2)//第几阶段
                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
                    @Override
                    public void onSuccess(Response<DetailPassBean> response) {
                        DetailPassBean body = response.body();
                        if (body.status.equals("200")) {
                            stagename2.setText(body.sceneDetail.getStage_name());
                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                try {
                                    DetailBean detailBean = new DetailBean();
                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
                                    detailBean.setStage(body.sceneDetail.getStage());
                                    detailBean.setSort(count++);
                                    detailBeanList2.add(detailBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            DetailAdapter detailAdapter2 = new DetailAdapter(detailBeanList2, getActivity());
                            stagerv2.setAdapter(detailAdapter2); //dialog.show
                            LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
                            stagerv2.setLayoutManager(layoutManager2);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<DetailPassBean> response) {
                        super.onError(response);
                        System.out.println("测试2：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });


    }


    public void initdetail3(int scode) {
        System.out.println("scene_id=" + scode);
        List<DetailBean> detailBeanList3 = new ArrayList<>();
        OkGo.<DetailPassBean>post(UrlConstants.Detail)
                .params("scene_id", scode)//场景编号
                .params("stage", 3)//第几阶段
                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
                    @Override
                    public void onSuccess(Response<DetailPassBean> response) {
                        DetailPassBean body = response.body();
                        if (body.status.equals("200")) {
                            stagename3.setText(body.sceneDetail.getStage_name());
                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                try {
                                    DetailBean detailBean = new DetailBean();
                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
                                    detailBean.setStage(body.sceneDetail.getStage());
                                    detailBean.setSort(count++);
                                    detailBeanList3.add(detailBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            DetailAdapter detailAdapter3 = new DetailAdapter(detailBeanList3, getActivity());
                            stagerv3.setAdapter(detailAdapter3); //dialog.show
                            LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
                            stagerv3.setLayoutManager(layoutManager3);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<DetailPassBean> response) {
                        super.onError(response);
                        System.out.println("测试3：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public void initdetail4(int scode) {
        System.out.println("scene_id=" + scode);
        List<DetailBean> detailBeanList4 = new ArrayList<>();
        OkGo.<DetailPassBean>post(UrlConstants.Detail)
                .params("scene_id", scode)//场景编号
                .params("stage", 4)//第几阶段
                .execute(new GsonCallback<DetailPassBean>(DetailPassBean.class) {
                    @Override
                    public void onSuccess(Response<DetailPassBean> response) {
                        DetailPassBean body = response.body();
                        if (body.status.equals("200")) {
                            stagename4.setText(body.sceneDetail.getStage_name());
                            for (int i = 0; i < body.sceneDetail.getSceneDetailContents().size(); i++) {//最多访问一页的条数
                                System.out.println("数据第" + i + "次输出:" + body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                try {
                                    DetailBean detailBean = new DetailBean();
                                    detailBean.setContext(body.sceneDetail.getSceneDetailContents().get(i).getContent());
                                    detailBean.setNumber(body.sceneDetail.getSceneDetailContents().get(i).getNumber());
                                    detailBean.setScenecode(body.sceneDetail.getScene_id());
                                    detailBean.setStage(body.sceneDetail.getStage());
                                    detailBean.setSort(count++);
                                    detailBeanList4.add(detailBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            DetailAdapter detailAdapter4 = new DetailAdapter(detailBeanList4, getActivity());
                            stagerv4.setAdapter(detailAdapter4); //dialog.show
                            LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
                            stagerv4.setLayoutManager(layoutManager4);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "信息有误，请检查登录情况~", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<DetailPassBean> response) {
                        super.onError(response);
                        System.out.println("测试4：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
