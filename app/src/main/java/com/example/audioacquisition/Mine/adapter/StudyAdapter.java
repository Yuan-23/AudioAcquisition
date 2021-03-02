package com.example.audioacquisition.Mine.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioacquisition.Mine.activity.ScoreActivity;
import com.example.audioacquisition.Mine.bean.Study;
import com.example.audioacquisition.R;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.ViewHolder> {
    private List<Study> studyList;
    private View mview;


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sname;
        TextView sscore;

        private ViewHolder(View view) {
            super(view);

            sname = (TextView) view.findViewById(R.id.item_study_name);
            sscore = (TextView) view.findViewById(R.id.item_study_score);
        }
    }


    public StudyAdapter(List<Study> studies) {
        studyList = studies;
    }


    public StudyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_study, parent, false);
        mview = view;
        StudyAdapter.ViewHolder dholder = new StudyAdapter.ViewHolder(view);
        return dholder;


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(final StudyAdapter.ViewHolder holder, int position) {
        final Study study = studyList.get(position);
        holder.sscore.setText(study.getScore() + "分");
        holder.sname.setText(study.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mview.getContext(), ScoreActivity.class);
                intent.putExtra("examid", study.getExamid());
                intent.putExtra("examscore", study.getScore());
                startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return studyList.size();
    }
}