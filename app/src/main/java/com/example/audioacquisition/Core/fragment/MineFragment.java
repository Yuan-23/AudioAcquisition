package com.example.audioacquisition.Core.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Mine.activity.ChangeActivity;
import com.example.audioacquisition.Mine.activity.CourceActivity;
import com.example.audioacquisition.Practice.activity.FaxActivity;
import com.example.audioacquisition.Mine.activity.LoginActivity;
import com.example.audioacquisition.Mine.activity.PracticeActivity;
import com.example.audioacquisition.Mine.activity.StudyActivity;
import com.example.audioacquisition.R;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private LinearLayout mlogin;
    private LinearLayout mchange;
    private LinearLayout mcource;
    private LinearLayout mpractice;
    private LinearLayout mstudy;
    private TextView mname;
    private TextView mlogintv;

    private Banner banner;
    //存轮播图
    ArrayList<Integer> imagPath = new ArrayList<>();

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mlogin = (LinearLayout) view.findViewById(R.id.mine_login_ll);
        mchange = (LinearLayout) view.findViewById(R.id.mine_change_ll);
        mstudy = (LinearLayout) view.findViewById(R.id.mine_study_ll);
        mcource = (LinearLayout) view.findViewById(R.id.mine_course_ll);
        mpractice = (LinearLayout) view.findViewById(R.id.mine_practice_ll);
        mname = (TextView) view.findViewById(R.id.mine_username);
        mlogintv=(TextView)view.findViewById(R.id.mine_login_tv);

        if (SharedPreferencesHelper.getLoginStatus(getContext()).equals(true)) {//已登录
            mname.setText(SharedPreferencesHelper.getUserBean(getContext()).getName());
            mlogintv.setText("重  新  登  录");
        } else {
            mname.setText("您还未登录");
            mlogintv.setText("登        录");
        }

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        mchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), ChangeActivity.class);
                startActivity(intent2);
            }
        });
        mcource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), CourceActivity.class);
                startActivity(intent3);
            }
        });
        mpractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity(), PracticeActivity.class);
                startActivity(intent4);
            }
        });
        mstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getActivity(), StudyActivity.class);
                startActivity(intent5);
            }
        });

        return view;
    }

}
