package com.example.audioacquisition.Fax.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.adapter.FaxAdapter;
import com.example.audioacquisition.Core.bean.FaxBean;
import com.example.audioacquisition.Core.bean.FaxPassBean;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.activity.InfoActivity;
import com.example.audioacquisition.Practice.passbean.PassBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;

public class FaxActivity extends AppCompatActivity {
    Toolbar toolbar;

    LinearLayout fax_vv;
    LinearLayout fax_pic;
    EditText fax_title;
    TextView certain;
    ImageView faximage;
    ImageView faxvideo;
    File mImg;
    private static final int ALBUM_REQUEST_CODE = 100;
    public static final int RC_CHOOSE_PHOTO = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fax);
        fax_vv = (LinearLayout) findViewById(R.id.fax_putvv);
        fax_pic = (LinearLayout) findViewById(R.id.fax_putpic);
        fax_title = (EditText) findViewById(R.id.fax_edt);
        certain = (TextView) findViewById(R.id.fax_certain);
        faximage = (ImageView) findViewById(R.id.fax_image);
        faxvideo = (ImageView) findViewById(R.id.fax_video);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });

        fax_vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitVideo();
            }
        });

        fax_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPicFromAlbum();
            }
        });

        certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickCertain();
            }
        });
    }

    public void ClickCertain() {
        if (TextUtils.isEmpty(fax_title.getText())) {
            Toast.makeText(FaxActivity.this, "请填写标题~", Toast.LENGTH_SHORT).show();
        } else if (true) {
            Toast.makeText(FaxActivity.this, "请选择视频~", Toast.LENGTH_SHORT).show();
        } else if (mImg.toString().equals(null)) {
            Toast.makeText(FaxActivity.this, "请选择封面~", Toast.LENGTH_SHORT).show();
        } else {
            OkGo.<PassBean>post(UrlConstants.ADDFax)
                    .params("user_id", SharedPreferencesHelper.getUserBean(getApplicationContext()).getId())
                    .params("district_id", SharedPreferencesHelper.getUserBean(getApplicationContext()).getDistrict_id())
                    .params("title", fax_title.getText().toString())
//                    .params("video",)
                    .params("picture", mImg)
                    .execute(new GsonCallback<PassBean>(PassBean.class) {
                        @Override
                        public void onSuccess(Response<PassBean> response) {
                            PassBean body = response.body();
                            if (body.status.equals("200")) {
                                Toast.makeText(getApplicationContext(), "上传成功~", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (body.status.equals("500")) {
                                Toast.makeText(getApplicationContext(), "上传失败，请重试~", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<PassBean> response) {
                            super.onError(response);
                            System.out.println("测试1：网络失败");
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {//获取图片
            Uri pictureUri = getUri(data);
            if (pictureUri == null) {
                return;
            }
            //获取图片的路径：
            String[] photoPath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pictureUri, photoPath, null, null, null);
            // Cursor cursor = managedQuery(pictureUri, photoPath, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            displayImage(path);
        }
        if (resultCode == RESULT_OK && null != data && requestCode == 2) {
            Uri uri = data.getData();
            String path = getRealPathFromURI(uri);
            Log.d("path", "path==" + path);
            File file = new File(path);
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();//实例化MediaMetadataRetriever对象
            mmr.setDataSource(file.getAbsolutePath());
            Bitmap bitmap = mmr.getFrameAtTime();//获得视频第一帧的Bitmap对象
            String duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);//时长(毫秒)
            Log.d("ddd", "duration==" + duration);
            int int_duration = Integer.parseInt(duration);
            if (int_duration > 11000) {
                Toast.makeText(getApplicationContext(), "视频时长已超过10秒，请重新选择", Toast.LENGTH_SHORT).show();
                return;
            }
        }
//        if (resultCode == RESULT_OK && null != data && requestCode == 3) {
//            Uri uri = data.getData();
//            ContentResolver cr = this.getContentResolver();
//
//            /** 数据库查询操作。
//             * 第一个参数 uri：为要查询的数据库+表的名称。
//             * 第二个参数 projection ： 要查询的列。
//             * 第三个参数 selection ： 查询的条件，相当于SQL where。
//             * 第三个参数 selectionArgs ： 查询条件的参数，相当于 ？。
//             * 第四个参数 sortOrder ： 结果排序。
//             */
//            assert uri != null;
//            Cursor cursor = cr.query(uri, null, null, null, null);
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    // 视频ID:MediaStore.Audio.Media._ID
//                    int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
//                    // 视频名称：MediaStore.Audio.Media.TITLE
//                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
//                    // 视频路径：MediaStore.Audio.Media.DATA
//                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
//                    // 视频时长：MediaStore.Audio.Media.DURATION
//                    int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
//                    // 视频大小：MediaStore.Audio.Media.SIZE
//                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
//                    Log.e("size ", size + "");
//                    // 视频缩略图路径：MediaStore.Images.Media.DATA
//                    String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//                    // 缩略图ID:MediaStore.Audio.Media._ID
//                    int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
//                    // 方法一 Thumbnails 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
//                    // 第一个参数为 ContentResolver，第二个参数为视频缩略图ID， 第三个参数kind有两种为：MICRO_KIND和MINI_KIND 字面意思理解为微型和迷你两种缩略模式，前者分辨率更低一些。
//                    Bitmap bitmap1 = MediaStore.Video.Thumbnails.getThumbnail(cr, imageId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
//
//                    // 方法二 ThumbnailUtils 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
//                    // 第一个参数为 视频/缩略图的位置，第二个依旧是分辨率相关的kind
//                    Bitmap bitmap2 = ThumbnailUtils.createVideoThumbnail(imagePath, MediaStore.Video.Thumbnails.MICRO_KIND);
//                    // 如果追求更好的话可以利用 ThumbnailUtils.extractThumbnail 把缩略图转化为的制定大小
//                    if (duration > 11000) {
//                        Toast.makeText(getApplicationContext(), "视频时长已超过10秒，请重新选择", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//                cursor.close();
//            }

    }

    /**
     * 从相册获取视频
     */

    public void InitVideo() {//打开视频相册
        //选择视频
        if (android.os.Build.BRAND.equals("Huawei")) {
            Intent intentPic = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentPic, 2);
        }
        if (android.os.Build.BRAND.equals("Xiaomi")) {//是否是小米设备,是的话用到弹窗选取入口的方法去选取视频
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "video/*");
            startActivityForResult(Intent.createChooser(intent, "选择要导入的视频"), 2);
        } else {//直接跳到系统相册去选取视频
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT < 19) {
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
            } else {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("video/*");
            }
            startActivityForResult(Intent.createChooser(intent, "选择要导入的视频"), 2);
        }

    }


    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    /**
     * 从相册获取图片
     */
    private void getPicFromAlbum() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //未授权，申请授权(从相册选择图片需要读取存储卡的权限)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_CHOOSE_PHOTO);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, ALBUM_REQUEST_CODE);

        }

    }


    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 解决小米手机上获取图片路径为null的情况
     *
     * @param intent
     * @return
     */
    public Uri getUri(android.content.Intent intent) {
        Uri uri = intent.getData();
        String type = null;
        try {
            type = intent.getType();

            if (uri.getScheme().equals("file") && (type.contains("image/"))) {
                String path = uri.getEncodedPath();
                if (path != null) {
                    path = Uri.decode(path);
                    ContentResolver cr = this.getContentResolver();
                    StringBuffer buff = new StringBuffer();
                    buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                            .append("'" + path + "'").append(")");
                    Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{MediaStore.Images.ImageColumns._ID},
                            buff.toString(), null, null);
                    int index = 0;
                    for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                        index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                        // set _id value
                        index = cur.getInt(index);
                    }
                    if (index == 0) {
                        // do nothing
                    } else {
                        Uri uri_temp = Uri
                                .parse("content://media/external/images/media/"
                                        + index);
                        if (uri_temp != null) {
                            uri = uri_temp;
                        }
                    }
                }
            }
            return uri;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void displayImage(String imagePath) {

        mImg = new File(imagePath);

        Glide.with(FaxActivity.this)
                .load(imagePath)
                .into(faximage);
    }

}