package com.example.audioacquisition.Core.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.audioacquisition.Practice.activity.KindActivity;
import com.example.audioacquisition.R;

//训练中心

public class PracticeFragment extends Fragment {
    LinearLayout kind1;
    LinearLayout kind2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        kind1 = (LinearLayout) view.findViewById(R.id.practice_kind1);
        kind2 = (LinearLayout) view.findViewById(R.id.practice_kind2);


        kind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KindActivity.class);
                intent.putExtra("kind", 2);
                startActivity(intent);
            }
        });
        kind2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KindActivity.class);
                intent.putExtra("kind", 3);
                startActivity(intent);
            }
        });

        return view;

    }


}
