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
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.passbean.DepartPassBean;
import com.example.audioacquisition.Mine.passbean.InfoBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoActivity extends AppCompatActivity {
    // 列表数据
    private List<String> popdepartname=new ArrayList<String>();
    private List<Integer> popdepartid=new ArrayList<Integer>();
    private int departchangeid;
    private String departchangename;

    // 点击此文本出现下拉popWindow
    private TextView mTextView;


    private Toolbar toolbar;
    private EditText Name;
    private EditText Number;
    private CircleImageView HeadImage;
    private LinearLayout imagechoose;
    private TextView certain;

    File mHeadImg;

    private static final int ALBUM_REQUEST_CODE = 100;
    public static final int RC_CHOOSE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Name = (EditText) findViewById(R.id.info_name_edt);
        Number = (EditText) findViewById(R.id.info_number_edt);
        HeadImage = (CircleImageView) findViewById(R.id.info_image_circle);
        mTextView = (TextView) findViewById(R.id.info_depart);
        imagechoose = (LinearLayout) findViewById(R.id.info_image);
        certain = (TextView) findViewById(R.id.info_certain);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });
        //初始化
        departchangeid = SharedPreferencesHelper.getDepartmentid(getApplicationContext());
        departchangename = SharedPreferencesHelper.getUserBean(getApplicationContext()).getDepartment_name();

        //加载用户资料
        disply();
        System.out.println("1第一个Departmentid=" + SharedPreferencesHelper.getDepartmentid(getApplicationContext()));
        System.out.println("1第三个Departmentname=" + SharedPreferencesHelper.getUserBean(getApplicationContext()).getDepartment_name());

        System.out.println("个人信息=" + SharedPreferencesHelper.getUserBean(getApplicationContext()).toString());

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
                System.out.println("信息上传");
                onClickPicture();
                System.out.println("头像上传");
                ChangeDepart();
                System.out.println("修改部门");
                finish();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        // 加载部门信息
        initDepart();

        //获取屏幕宽度
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //设置popupWindow的宽度，这几个数字是根据布局中的textView权重得出的4.2表示这一行的总权重，3.1表示textView的权重
        int with = (int) ((metrics.widthPixels / 4.2) * 2);
//        int with = (int) ((metrics.widthPixels / 4.2) * 3.1);

        // 找到需要填充到pop的布局
        View view = LayoutInflater.from(this).inflate(R.layout.item_poplist, null);
        // 根据此布局建立pop
        final PopupWindow popupWindow = new PopupWindow(view);
        // <<<<<<<<<<<<<<<<<<<极其重要>>>>>>>>>>>>>>>>>>>>>
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(with);

        //这样设置pop才可以缩回去
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // 填充此布局上的列表
        ListView listView = (ListView) view.findViewById(R.id.pop_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_content, popdepartname);
        listView.setAdapter(adapter);

        // 当listView受到点击时替换mTextView当前显示文本
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mTextView.setText(popdepartname.get(arg2));
                departchangeid = popdepartid.get(arg2);
                departchangename = popdepartname.get(arg2);
                popupWindow.dismiss();
            }
        });

        // 当mTextView受到点击时显示pop
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v);
            }
        });
    }


    public void initDepart() {
        OkGo.<DepartPassBean>post(UrlConstants.Department)
                .params("district_id", SharedPreferencesHelper.getUserBean(InfoActivity.this).getDistrict_id())
                .execute(new GsonCallback<DepartPassBean>(DepartPassBean.class) {
                    @Override
                    public void onSuccess(Response<DepartPassBean> response) {
                        DepartPassBean body = response.body();
                        if (body.status.equals("200")) {
                            try {
                                for (int i = 0; i < body.departments.size(); i++) {
                                    popdepartname.add(body.departments.get(i).getDepart_name()) ;
                                    popdepartid.add(body.departments.get(i).getId()) ;
//                                Depart depart = new Depart();
//                                depart.setDepart(body.departments.get(i).getDepart_name());
//                                depart.setDepartid(body.departments.get(i).getId());
//                                departs.add(depart);

                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


//                            //  建立Adapter绑定数据源
//                            DepartAdapter departAdapter = new DepartAdapter(InfoActivity.this, departs);
//                            //绑定Adapter
//                            spinner.setAdapter(departAdapter);

                        } else if (body.status.equals("500")) {
                            Toast.makeText(InfoActivity.this, "系统繁忙，请重试~", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<DepartPassBean> response) {
                        super.onError(response);
                        System.out.println("资料测试4：网络失败");
                        Toast.makeText(InfoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void ChangeDepart() {//ChangeDepart
        OkGo.<InfoBean>post(UrlConstants.ChangeDepart)
                .params("user_id", SharedPreferencesHelper.getUserId(InfoActivity.this))
                .params("depart_id", departchangeid)
                .execute(new GsonCallback<InfoBean>(InfoBean.class) {
                    @Override
                    public void onSuccess(Response<InfoBean> response) {
                        InfoBean body = response.body();
                        if (body.status.equals("200")) {
                            SharedPreferencesHelper.setUserBean(body.user, InfoActivity.this);
                            SharedPreferencesHelper.setDepartmentid(departchangeid, getApplicationContext());

                            System.out.println("2第一个Departmentid=" + SharedPreferencesHelper.getDepartmentid(getApplicationContext()));
                            System.out.println("2第三个Departmentname=" + SharedPreferencesHelper.getUserBean(getApplicationContext()).getDepartment_name());

                        } else if (body.status.equals("500")) {
                            Toast.makeText(InfoActivity.this, "请将您的信息补充完整~", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<InfoBean> response) {
                        super.onError(response);
                        System.out.println("资料测试4：网络失败");
                        Toast.makeText(InfoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onClickCertain() {
        if (TextUtils.isEmpty(Name.getText())) {
            Toast.makeText(InfoActivity.this, "请填写您的昵称~", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Number.getText())) {
            Toast.makeText(InfoActivity.this, "请填写您的电话号码~", Toast.LENGTH_SHORT).show();
        } else {
            OkGo.<InfoBean>post(UrlConstants.Information)
                    .params("user_id", SharedPreferencesHelper.getUserId(InfoActivity.this))
                    .params("nickname", Name.getText().toString())
                    .params("phone_number", Number.getText().toString())
                    .execute(new GsonCallback<InfoBean>(InfoBean.class) {
                        @Override
                        public void onSuccess(Response<InfoBean> response) {
                            InfoBean body = response.body();
                            if (body.status.equals("200")) {
                                SharedPreferencesHelper.setUserBean(body.user, InfoActivity.this);
                                Toast.makeText(InfoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                            } else if (body.status.equals("500")) {
                                Toast.makeText(InfoActivity.this, "请将您的信息补充完整~", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<InfoBean> response) {
                            super.onError(response);
                            System.out.println("资料测试4：网络失败");
                            Toast.makeText(InfoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    public void onClickPicture() {

        if (mHeadImg != null) {
            OkGo.<InfoBean>post(UrlConstants.Portrait)
                    .params("user_id", SharedPreferencesHelper.getUserId(InfoActivity.this))
                    .params("headPortrait ", mHeadImg)
                    .execute(new GsonCallback<InfoBean>(InfoBean.class) {
                        @Override
                        public void onSuccess(Response<InfoBean> response) {
                            InfoBean body = response.body();
                            if (body.status.equals("200")) {
                                SharedPreferencesHelper.setUserBean(body.user, InfoActivity.this);
                                SharedPreferencesHelper.setUserAvatarUrl(body.user.getHead_portrait(),InfoActivity.this);
                            } else if (body.status.equals("500")) {
                                Toast.makeText(InfoActivity.this, "系统繁忙，请重新上传头像~", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<InfoBean> response) {
                            super.onError(response);
                            System.out.println("网络失败");
                            Toast.makeText(InfoActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                    });
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

        Name.setText(SharedPreferencesHelper.getUserBean(InfoActivity.this).getNickname());
        Glide.with(InfoActivity.this)
                .load(SharedPreferencesHelper.getUserBean(InfoActivity.this).getHead_portrait())
                .into(HeadImage);
        Number.setText(SharedPreferencesHelper.getUserBean(InfoActivity.this).getPhone_number());
        // 初始化显示
        mTextView.setText(SharedPreferencesHelper.getUserBean(getApplicationContext()).getDepartment_name());

    }


    private void displayImage(String imagePath) {

        mHeadImg = new File(imagePath);

        Glide.with(InfoActivity.this)
                .load(imagePath)
                .into(HeadImage);
    }
}
