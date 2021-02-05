package com.example.audioacquisition.Mine.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Mine.bean.Cource;
import com.example.audioacquisition.Mine.bean.Study;
import com.example.audioacquisition.R;

import java.util.List;

import static android.graphics.Color.GRAY;


public class CourceAdapter extends RecyclerView.Adapter<CourceAdapter.ViewHolder> {
    private List<Cource> courceList;
    private View mview;


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cname;

        private ViewHolder(View view) {
            super(view);

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
        holder.cname.setText(cource.getCourcename());
        if (cource.getLearnflag() == 0) {//没学过
            holder.cname.setTextColor(R.color.theme3);
        } else if (cource.getLearnflag() == 1) {//学过
            holder.cname.setTextColor(R.color.theme);
        }

    }

    public int getItemCount() {
        return courceList.size();
    }
}