package com.example.audioacquisition.Mine.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Mine.bean.User;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText Name;
    private EditText Number;
    private Spinner Spinner;
    String Typetext = null;
    private CircleImageView HeadImage;

    private LinearLayout imagechoose;
    private TextView certain;

    File mHeadImg;
    User UserBean;
    private static final int ALBUM_REQUEST_CODE = 100;
    public static final int RC_CHOOSE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Name = (EditText) findViewById(R.id.info_name_edt);
        Number = (EditText) findViewById(R.id.info_number_edt);
        HeadImage = (CircleImageView) findViewById(R.id.info_image_circle);
        Spinner = (Spinner) findViewById(R.id.info_spinner);

        imagechoose = (LinearLayout) findViewById(R.id.info_image);
        certain = (TextView) findViewById(R.id.info_certain);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });

        disply();//加载用户资料


        imagechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPicFromAlbum();
            }
        });

        certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCertain();
            }
        });


        Resources res = getResources();
        String[] city = res.getStringArray(R.array.spingarr);//将province中内容添加到数组city中
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, city);//创建Arrayadapter适配器

        Spinner.setAdapter(adapter);

        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//通过此方法为下拉列表设置点击事件
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = Spinner.getItemAtPosition(i).toString();
                switch (text) {
                    case "A部":
                        break;
                    case "B部":
                        break;
                    case "C部":
                        break;
                    case "D部":
                        break;
                    case "E部":
                        break;
                    case "F部":
                        break;
                    case "G部":
                        break;
                    case "H部":
                        break;
                }
                Typetext = text;//设置类别

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    public void onClickCertain() {

        if (TextUtils.isEmpty(Name.getText())) {
            Toast.makeText(InfoActivity.this, "请填写您的昵称~", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Number.getText())) {
            Toast.makeText(InfoActivity.this, "请填写您的电话号码~", Toast.LENGTH_SHORT).show();
        } else {
//            OkGo.<PassUserBean>post(UrlConstants.MineInfo)
//                    .params("userNickname", Name.getText().toString())
//                    .params("id", SharedPreferencesHelper.getUserBean().getId())
//                    .params("userPhoneNumber", Number.getText().toString())
//                    .params("userEmail", Introduce.getText().toString())
//                    .params("headPortrait", mHeadImg)//HeadImage.getDrawable().toString()
//                    .execute(new GsonCallback<PassUserBean>(PassUserBean.class) {
//                        @Override
//                        public void onSuccess(Response<PassUserBean> response) {
//                            PassUserBean body = response.body();
//                            UserBean = body.user;
//
//                            if (body.status.equals("200")) {
//                                Toast.makeText(InfoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
//                                SharedPreDataHelper.setUserBean(body.user);
//                                SharedPreDataHelper.setUserName(body.user.getUserNickname());
//                                SharedPreDataHelper.setUserPhone(body.user.getUserPhoneNumber());
//                                SharedPreDataHelper.setEmail(body.user.getUserEmail());
//
//                            } else if (body.status.equals("500")) {
//                                Toast.makeText(InfoActivity.this, "请将您的信息补充完整~", Toast.LENGTH_SHORT).show();
//                            }
//
//                            finish();
//                        }
//
//                        @Override
//                        public void onError(Response<PassUserBean> response) {
//                            super.onError(response);
//                            System.out.println("资料测试4：网络失败");
//                            Toast.makeText(InfoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                        }
//                    });
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {
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

    private void disply() {
//        if (UserBean != null) {
//            Name.setText(UserBean.getName());
//            Glide.with(InfoActivity.this)
//                    .load(UserBean.getUserHeadPortrait())
//                    .into(HeadImage);
//            Number.setText(UserBean.getPolice_number());
//        }else if(SharedPreDataHelper.getUserBean()!=null){
//            Name.setText(SharedPreDataHelper.getUserBean().getUserNickname());
//            Glide.with(InfoActivity.this)
//                    .load(SharedPreDataHelper.getUserBean().getUserHeadPortrait())
//                    .into(HeadImage);
//            Number.setText(SharedPreDataHelper.getUserBean().getUserAccount());
//            Introduce.setText(SharedPreDataHelper.getUserBean().getUserEmail());
//        }

    }


    private void displayImage(String imagePath) {

        mHeadImg = new File(imagePath);

        Glide.with(InfoActivity.this)
                .load(imagePath)
                .into(HeadImage);

        // SharedPreUtil.setString(this, SharedPreConstants.USER_AVATAR_URL, imagePath);
    }
}
