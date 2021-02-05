package com.example.audioacquisition.Home.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Home.activity.HorizontalActivity;
import com.example.audioacquisition.Home.bean.ThirdBean;
import com.example.audioacquisition.R;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.ViewHolder> {
    private List<ThirdBean> thirdBeanList;
    private View mview;


    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView video;
      //  VideoView audio;


        private ViewHolder(View view) {
            super(view);

            video = (VideoView) view.findViewById(R.id.item_third_video);
        }
    }


    public ThirdAdapter(List<ThirdBean> thirdBeans) {
        thirdBeanList = thirdBeans;
    }


    public ThirdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_third, parent, false);
        mview = view;
        ThirdAdapter.ViewHolder dholder = new ThirdAdapter.ViewHolder(view);
        return dholder;


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(final ThirdAdapter.ViewHolder holder, int position) {
        final ThirdBean thirdBean = thirdBeanList.get(position);
        //网络数据
        Uri uri1 = Uri.parse("rtsp://v2.cache2.c.youtube.com/CjgLENy73wIaLwm3JbT_%ED%AF%80%ED%B0%819HqWohMYESARFEIJbXYtZ29vZ2xlSARSB3Jlc3VsdHNg_vSmsbeSyd5JDA==/0/0/0/video.3gp");
        holder.video.setMediaController(new MediaController(mview.getContext()));
        holder.video.setVideoURI(uri1);
      //  holder.video.start();
        holder.video.requestFocus();

//        //本地数据
//        Uri uri2 = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/bendi.mp4");
//        holder.video.setMediaController(new MediaController(mview.getContext()));
//        holder.video.setVideoURI(uri2);
//        holder.video.start();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mview.getContext(), HorizontalActivity.class);
                startActivity(intent);
            }
        });


    }

    public int getItemCount() {
        return thirdBeanList.size();
    }
}

