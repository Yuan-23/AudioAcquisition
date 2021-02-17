package com.example.audioacquisition.Study.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Study.bean.OtherBean;
import com.example.audioacquisition.R;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {
    private List<OtherBean> otherBeanList ;
    private View mview;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView otv;
        VideoView ovv;
        TextView oname;

        private ViewHolder(View view) {
            super(view);
            oname = (TextView) view.findViewById(R.id.item_other_name);
            otv = (TextView) view.findViewById(R.id.item_other_tv);
            ovv = (VideoView) view.findViewById(R.id.item_other_vv);
        }
    }

    public OtherAdapter(List<OtherBean> otherBeans) {
        otherBeanList = otherBeans;
    }


    public OtherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_other, parent, false);
        mview = view;
        OtherAdapter.ViewHolder dholder = new OtherAdapter.ViewHolder(view);
        return dholder;

    }

    public void onBindViewHolder(final OtherAdapter.ViewHolder holder, int position) {
        final OtherBean otherBean = otherBeanList.get(position);
        holder.oname.setText(otherBean.getOthername());
        holder.otv.setText(otherBean.getOthercontent());

        //视频
        String videoUrl = otherBean.getOthervideo();
        Uri uri = Uri.parse(videoUrl);
        holder.ovv.setVideoURI(uri);
        //创建MediaController对象
        MediaController mediaController = new MediaController(mview.getContext());
        //VideoView与MediaController建立关联
        holder.ovv.setMediaController(mediaController);
        //让VideoView获取焦点
        holder.ovv.requestFocus();


    }

    public int getItemCount() {
        return otherBeanList.size();
    }
}
