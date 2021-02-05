package com.example.audioacquisition.Practice.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.audioacquisition.Core.bean.KindBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Practice.adapter.SecurityAdapter;
import com.example.audioacquisition.Practice.adapter.TrafficAdapter;
import com.example.audioacquisition.Practice.bean.TrafficBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class TrafficFragment extends Fragment {
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;
    private RecyclerView trafficrv;
    private RecyclerView securityrv;
    List<TrafficBean> trafficBeanList1 = new ArrayList<>();
    List<TrafficBean> trafficBeanList2 = new ArrayList<>();

    //在fragment内部写一个静态方法,返回自己;供外部调用;
    public static Fragment getInstance(String title) {
        TrafficFragment tFragment = new TrafficFragment();
        Bundle bundle = new Bundle();
        bundle.putString("T_title", title);
        tFragment.setArguments(bundle);
        return tFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        Bundle bundle = getArguments();
        // 根据标题是否相同请求不同的接口;
        if (bundle.getString("T_title").equals("交通")) {
            view = inflater.inflate(R.layout.fragment_traffic, container, false);
            trafficrv = (RecyclerView) view.findViewById(R.id.traffic_rv);
            Permit();//申请权限

            init1();



        } else if (bundle.getString("T_title").equals("治安")) {

            view = inflater.inflate(R.layout.fragment_security, container, false);
            securityrv = (RecyclerView) view.findViewById(R.id.security_rv);

            Permit();//申请权限

            init2();


        }
        return view;
    }

    public void init1() {
//        TrafficBean trafficBean1 = new TrafficBean("对机动车使用、变造号牌违法行为的现场处置",0);
//        trafficBeanList1.add(trafficBean1);
//        TrafficBean trafficBean2 = new TrafficBean("对非机动车不按交通信号行驶违法行为的现场处置",0);
//        trafficBeanList1.add(trafficBean2);
//        TrafficBean trafficBean3 = new TrafficBean("对不按规定使用安全带违法行为的现场处置",0);
//        trafficBeanList1.add(trafficBean3);
//        TrafficBean trafficBean4 = new TrafficBean("对未投保第三者强制保险车辆的现场处置",0);
//        trafficBeanList1.add(trafficBean4);

        System.out.println("类别为：" + 2);
        OkGo.<KindBean>post(UrlConstants.Teach)
                .params("sceneSortId", 2)//类别
                .execute(new GsonCallback<KindBean>(KindBean.class) {
                    @Override
                    public void onSuccess(Response<KindBean> response) {
                        KindBean body = response.body();
                        if (body.status.equals("200")) {
                            for (int i = 0; i < body.scene.size(); i++) {//最多访问一页的条数
                                System.out.println("数据ID第" + i + "次输出:" + body.scene.get(i).getId());
                                try {
                                    TrafficBean trafficBean = new TrafficBean();
                                    trafficBean.setVideoname(body.scene.get(i).getScene_name());
                                    trafficBean.setSceneSortId(body.scene.get(i).getScene_sort_id());
                                    trafficBeanList1.add(trafficBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            TrafficAdapter trafficAdapter = new TrafficAdapter(trafficBeanList1);
                            trafficrv.setAdapter(trafficAdapter); //dialog.show
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            trafficrv.setLayoutManager(layoutManager);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "请确认登录情况", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<KindBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
                        Toast.makeText(getContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void init2() {
//        TrafficBean trafficBean1 = new TrafficBean("对跨越警戒线违法行为的现场处置");
//        trafficBeanList2.add(trafficBean1);
//        TrafficBean trafficBean2 = new TrafficBean("对盘查对象反复索要执法证件阻碍民警执法的现场处置");
//        trafficBeanList2.add(trafficBean2);
//        TrafficBean trafficBean3 = new TrafficBean("对行人翻越护栏的现场处置");
//        trafficBeanList2.add(trafficBean3);
//        TrafficBean trafficBean4 = new TrafficBean("对跨越警戒线违法行为的现场处置");
//        trafficBeanList2.add(trafficBean4);

        System.out.println("类别为：" + 3);
        OkGo.<KindBean>post(UrlConstants.Teach)
                .params("sceneSortId", 3)//类别
                .execute(new GsonCallback<KindBean>(KindBean.class) {
                    @Override
                    public void onSuccess(Response<KindBean> response) {
                        KindBean body = response.body();
                        if (body.status.equals("200")) {
                            for (int i = 0; i < body.scene.size(); i++) {//最多访问一页的条数
                                System.out.println("数据ID第" + i + "次输出:" + body.scene.get(i).getId());
                                try {
                                    TrafficBean trafficBean = new TrafficBean();
                                    trafficBean.setVideoname(body.scene.get(i).getScene_name());
                                    trafficBean.setSceneSortId(body.scene.get(i).getScene_sort_id());
                                    trafficBeanList2.add(trafficBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            SecurityAdapter securityAdapter = new SecurityAdapter(trafficBeanList2);
                            securityrv.setAdapter(securityAdapter); //dialog.show
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            securityrv.setLayoutManager(layoutManager);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(getContext(), "请确认登录情况", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<KindBean> response) {
                        super.onError(response);
                        System.out.println("登录测试4：网络失败");
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

}
