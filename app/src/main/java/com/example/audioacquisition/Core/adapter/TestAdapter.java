package com.example.audioacquisition.Core.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.R;
import com.example.audioacquisition.Test.activity.ExamActivity;
import com.example.audioacquisition.Test.activity.TestActivity;
import com.example.audioacquisition.Test.bean.TestBean;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<TestBean> testBeanList;
    private View mview;


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView testtv;
        TextView testtime;

        private ViewHolder(View view) {
            super(view);
            testtv = (TextView) view.findViewById(R.id.item_test_name);
            testtime = (TextView) view.findViewById(R.id.item_test_time);
        }
    }


    public TestAdapter(List<TestBean> testBeans) {
        testBeanList = testBeans;
    }


    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_test, parent, false);
        mview = view;
        TestAdapter.ViewHolder dholder = new TestAdapter.ViewHolder(view);
        return dholder;


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(final TestAdapter.ViewHolder holder, int position) {
        final TestBean testBean = testBeanList.get(position);

        holder.testtv.setText(testBean.getTest_name());
        holder.testtime.setText("截止日期：" + testBean.getTest_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mview.getContext(), TestActivity.class);
                intent.putExtra("sceneid",testBean.getSceneid());
                startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return testBeanList.size();
    }
}

