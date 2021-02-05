package com.example.audioacquisition.Mine.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.audioacquisition.Core.activity.MainActivity;
import com.example.audioacquisition.Core.data.SharedPreferencesUtil;
import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.LoginHelper;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.passbean.LoginBean;
import com.example.audioacquisition.R;
import com.google.android.material.textfield.TextInputEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private TextView login;
    private TextView forget;
    private ImageView back;
    private CheckBox remember;
    private TextInputEditText mUserAccountEt;
    private TextInputEditText mPasswordEt;

    File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (TextView) findViewById(R.id.login_login_tv);
        forget = findViewById(R.id.login_forgetpsw_tv);
        remember = findViewById(R.id.login_remember);
        mUserAccountEt = findViewById(R.id.login_account_et);
        mPasswordEt = findViewById(R.id.login_password_et);

        back = (ImageView) findViewById(R.id.back_image_log);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClickLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickForget();
            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        try {
            load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @SuppressLint("WrongConstant")
    public void onClickLogin() throws IOException {
        if (TextUtils.isEmpty(mUserAccountEt.getText())) {
            Toast.makeText(LoginActivity.this, "请输入电话号码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mPasswordEt.getText())) {
            Toast.makeText(LoginActivity.this, "请输入您的密码", Toast.LENGTH_SHORT).show();
        } else {
            OkGo.<LoginBean>post(UrlConstants.Login)
                    .params("police_number", mUserAccountEt.getText().toString())//警号
                    .params("password", mPasswordEt.getText().toString())//密码
                    .execute(new GsonCallback<LoginBean>(LoginBean.class) {
                        @Override
                        public void onSuccess(Response<LoginBean> response) {
                            LoginBean body = response.body();
                            System.out.println("登录测试1：" + body.toString());
                            if (body.status.equals("200")) {
                                SharedPreferencesHelper.setUserAccount(body.user.getPolice_number(), LoginActivity.this);
                                SharedPreferencesHelper.setUserPassWord(body.user.getPassword(), LoginActivity.this);
                                SharedPreferencesHelper.setUserId(body.user.getId(),LoginActivity.this);
                                SharedPreferencesHelper.setUserBean(body.user,LoginActivity.this);
                                SharedPreferencesHelper.setLoginStatus(true,LoginActivity.this);
                                LoginHelper.login(LoginActivity.this);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            } else if (body.status.equals("500")) {
                                System.out.println("登录测试3：" + body.toString());
                                Toast.makeText(LoginActivity.this, "您的账号或密码有误~", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<LoginBean> response) {
                            super.onError(response);
                            System.out.println("登录测试4：网络失败");
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                    });

            String name = mUserAccountEt.getText().toString();
            String pwd = mPasswordEt.getText().toString();
            FileOutputStream fos = null;
            // 判断是否有勾选记住密码
            if (remember.isChecked()) {
                try {
                    /*
                     * getFilesDir()路径其实就是data/data/项目包/files 安卓中，每一个程序只能在自己的包下进行读写。
                     * 例如，本例子中，其实路径就是 data/data/com.examle.mylogin/files/info.txt
                     * 这里补充一点，如果文件要写在sd卡上，那么路径为storage/sdcard/info.txt,注意，写在sd卡是要添加读写权限的。
                     * 当然咯，路径不用自己写，可以用api获取，Environment.getExternalStorageDirectory()
                     * android.permission.READ_EXTERNAL_STORAGE，android.permission.WRITE_EXTERNAL_STORAGE
                     */
                    file = new File(getFilesDir(), "info.txt");
                    fos = new FileOutputStream(file);
                    // 将name和pwd转化为字节数组写入。##是为了方便待会分割
                    fos.write((name + "##" + pwd).getBytes());

                } finally {
                    if (fos != null) {
                        fos.close();
                    }
                }

            } else {
                // 如果用户没有勾选记住密码，就判断file是否存在，存在就删除
                if (file.exists()) {
                    file.delete();
                    //  Toast.makeText(LoginActivity.this, "登陆成功", 0).show();
                }
            }
        }
    }


    // 加载账户密码的方法
    public void load() throws IOException {
        FileInputStream fiStream = null;
        BufferedReader br = null;
        file = new File(getFilesDir(), "info.txt");
        if (file.exists()) {
            try {
                fiStream = new FileInputStream(file);
                /* 将字节流转化为字符流，转化是因为我们知道info.txt
                 * 只有一行数据，为了使用readLine()方法，所以我们这里
                 * 转化为字符流，其实用字节流也是可以做的。但比较麻烦
                 */
                br = new BufferedReader(new InputStreamReader(fiStream));
                //读取info.txt
                String str = br.readLine();
                //分割info.txt里面的内容。这就是为什么写入的时候要加入##的原因
                String arr[] = str.split("##");
                mUserAccountEt.setText(arr[0]);
                mPasswordEt.setText(arr[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } else {

        }
    }

    public void onClickForget() {
        Intent intent = new Intent(LoginActivity.this, ChangeActivity.class);
        startActivity(intent);
    }

}
