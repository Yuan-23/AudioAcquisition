package com.example.audioacquisition.Study.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Study.activity.OtherdetailActivity;
import com.example.audioacquisition.Study.bean.OtherBean;
import com.example.audioacquisition.R;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {
    private List<OtherBean> otherBeanList;
    private View mview;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView oimg;
        TextView oname;

        private ViewHolder(View view) {
            super(view);
            oname = (TextView) view.findViewById(R.id.item_other_tv);
            oimg = (ImageView) view.findViewById(R.id.item_other_img);
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

        try {
            holder.oname.setText(otherBean.getOthername());
            Glide.with(mview.getContext())
                    .load(otherBean.getOtherimage())
                    .into(holder.oimg);

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mview.getContext(), OtherdetailActivity.class);
                intent.putExtra("id", otherBean.getOid());
                startActivity(intent);
            }
        });


    }

    public int getItemCount() {
        return otherBeanList.size();
    }
}
