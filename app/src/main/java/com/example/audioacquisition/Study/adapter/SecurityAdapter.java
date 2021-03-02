package com.example.audioacquisition.Study.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Study.activity.VideoActivity;
import com.example.audioacquisition.Study.bean.TrafficBean;
import com.example.audioacquisition.R;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class SecurityAdapter extends RecyclerView.Adapter<SecurityAdapter.ViewHolder> {
    private List<TrafficBean> trafficBeanList;
    private View mview;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        private ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.item_security_name);
        }
    }

    public SecurityAdapter(List<TrafficBean> trafficBeans) {
        trafficBeanList = trafficBeans;
    }


    public SecurityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_security, parent, false);
        mview = view;
        SecurityAdapter.ViewHolder dholder = new SecurityAdapter.ViewHolder(view);
        return dholder;

    }

    public void onBindViewHolder(final SecurityAdapter.ViewHolder holder, int position) {
        final TrafficBean trafficBean = trafficBeanList.get(position);
        holder.tv.setText(trafficBean.getVideoname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mview.getContext(), VideoActivity.class);
                intent.putExtra("sceneId", trafficBean.getSceneId());
                intent.putExtra("teachurl",trafficBean.getTeachurl());
                startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return trafficBeanList.size();
    }
}

