package com.example.audioacquisition.Mine.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Mine.bean.Cource;
import com.example.audioacquisition.Mine.bean.Study;
import com.example.audioacquisition.Practice.activity.HorizontalActivity;
import com.example.audioacquisition.R;

import java.util.List;

import static android.graphics.Color.GRAY;
import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class CourceAdapter extends RecyclerView.Adapter<CourceAdapter.ViewHolder> {
    private List<Cource> courceList;
    private View mview;


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cname;
        ImageView cimage;

        private ViewHolder(View view) {
            super(view);
            cimage = (ImageView) view.findViewById(R.id.item_cource_image);
            cname = (TextView) view.findViewById(R.id.item_cource_name);
        }
    }


    public CourceAdapter(List<Cource> cources) {
        courceList = cources;
    }


    public CourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cource, parent, false);
        mview = view;
        CourceAdapter.ViewHolder dholder = new CourceAdapter.ViewHolder(view);
        return dholder;


    }


    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(final CourceAdapter.ViewHolder holder, int position) {
        final Cource cource = courceList.get(position);

        try {
            holder.cname.setText(cource.getCourcename());
            Glide.with(mview.getContext())
                    .load(cource.getCourceiamge())
                    .into(holder.cimage);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    System.out.println("少时诵诗书所所所所area：" + cource.getArea());
//                    System.out.println("少时诵诗书所所所所code：" + cource.getCode());
//                    Intent intent = new Intent(mview.getContext(), HorizontalActivity.class);
//                    intent.putExtra("code", cource.getCode());
//                    intent.putExtra("area", cource.getArea());
//                    startActivity(intent);
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int getItemCount() {
        return courceList.size();
    }
}