package com.example.audioacquisition.Core.data;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.VideoView;


public class FllScreenVideoView extends VideoView {
    public FllScreenVideoView(Context context) {
        super(context);
    }

    public FllScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FllScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


}
