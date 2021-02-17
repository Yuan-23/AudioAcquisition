package com.example.audioacquisition.Core.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Core.bean.HomeBean;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Practice.activity.HorizontalActivity;
import com.example.audioacquisition.R;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeBean> homeBeanList;
    private View mview;


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fname;
        int fscode;
        int fareacode;

        private ViewHolder(View view) {
            super(view);

            fname = (TextView) view.findViewById(R.id.item_name);

        }
    }


    public HomeAdapter(List<HomeBean> homeBeans) {
        homeBeanList = homeBeans;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);
        mview = view;
        ViewHolder dholder = new ViewHolder(view);
        return dholder;


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final HomeBean homeBean = homeBeanList.get(position);
        holder.fname.setText(homeBean.getName());
        holder.fscode = homeBean.getScode();
        holder.fareacode = homeBean.getAreacode();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mview.getContext(), HorizontalActivity.class);
                SharedPreferencesHelper.setAreaCode(holder.fareacode, mview.getContext());
                SharedPreferencesHelper.setSceneCode(holder.fscode, mview.getContext());
                intent.putExtra("code", holder.fscode);
                intent.putExtra("area", holder.fareacode);
                startActivity(intent);
            }
        });


    }

    public int getItemCount() {
        return homeBeanList.size();
    }
}


