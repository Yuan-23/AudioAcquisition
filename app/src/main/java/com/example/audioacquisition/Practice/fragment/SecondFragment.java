//package com.example.audioacquisition.Home.fragment;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//
//import android.os.Environment;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.MediaController;
//
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//
//import com.example.audioacquisition.R;
//
//import java.io.File;
//
//
//public class SecondFragment extends Fragment {
//    private VideoView video;
//
//    //在fragment内部写一个静态方法,返回自己;供外部调用;
//    public static Fragment getInstance(String title) {
//        HorizontalFragment mFragment = new HorizontalFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("title", title);
//        mFragment.setArguments(bundle);
//        return mFragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_second, container, false);
//
//
//        video = (VideoView) view.findViewById(R.id.videoView);
//        System.out.println("视频1");
//        //判断sd卡是否存在
//        String SdPath = "/sdcard";
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            int REQUEST_CODE_CONTACT = 101;
//            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};//验证是否许可权限
//            for (String str : permissions) {
//                if (getActivity().checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
//                    //申请权限
//                    getActivity().requestPermissions(permissions, REQUEST_CODE_CONTACT);
//                }
//                //这里就是权限打开之后自己要操作的逻辑
//
//
//            }
//        }
//
//
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            //如果SD卡存在，则获取根目录
//            SdPath = Environment.getExternalStorageDirectory().toString();
//            System.out.println("视频2");
//        }
//
//        File file = new File(SdPath + "/Movies/drunkdriving.mp4");//获取SD卡上要播放的文件
//        MediaController mc = new MediaController(this.getActivity());
//        System.out.println("视频3");
//        if (file.exists()) {//判断要播放的视频是否存在
//            System.out.println("视频4");
//            video.setVideoPath(file.getAbsolutePath());//指定要播放的视频
//            video.setMediaController(mc);//设置VedioView与MediaController相关联
//            video.requestFocus();//让VedioView获得焦点
//            try {
//                video.getBackground().setAlpha(0);//将背景图片设为透明
//                video.start();//开始播放视频
//                System.out.println("视频5");
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//
//            //为VideoView添加完成事件监听器
//            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    //弹出消息提示框显示播放完毕
//                    System.out.println("视频6");
//                    Toast.makeText(getContext(), "视频播放完毕", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        } else {
//            System.out.println("视频7");
//            //弹出消息提示框显示文件不存在
//            Toast.makeText(getContext(), "要播放的视频文件不存在！", Toast.LENGTH_SHORT).show();
//        }
//
//
//        return view;
//
//    }
//}
