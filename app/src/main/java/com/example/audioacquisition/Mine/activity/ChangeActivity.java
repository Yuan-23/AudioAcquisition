package com.example.audioacquisition.Mine.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.audioacquisition.Core.data.UrlConstants;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.Core.network.okgo.GsonCallback;
import com.example.audioacquisition.Mine.passbean.ChangePswBean;
import com.example.audioacquisition.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class ChangeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText oldpsw;
    private EditText newpsw;
    private EditText repsw;
    private TextView certain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//顶部返回按钮
            }
        });

        oldpsw = (EditText) findViewById(R.id.change_oldpsw);
        newpsw = (EditText) findViewById(R.id.change_newpsw);
        repsw = (EditText) findViewById(R.id.change_repsw);
        certain = (TextView) findViewById(R.id.change_certain);

        certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newpsw.getText().toString().equals(repsw.getText().toString())) {
                    OnClickCertain();
                } else {
                    newpsw.setText(null);
                    repsw.setText(null);
                    Toast.makeText(ChangeActivity.this, "两次密码输入不一致哦，请重新输入！", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void OnClickCertain() {
        if (TextUtils.isEmpty(oldpsw.getText())) {
            Toast.makeText(ChangeActivity.this, "请输入原密码~", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(newpsw.getText())) {
            Toast.makeText(ChangeActivity.this, "请输入新密码~", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(repsw.getText())) {
            Toast.makeText(ChangeActivity.this, "请再次输入新密码~", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("用户账号" + SharedPreferencesHelper.getUserAccount(ChangeActivity.this));
            OkGo.<ChangePswBean>post(UrlConstants.ChangePsw)
                    .params("police_number", SharedPreferencesHelper.getUserAccount(ChangeActivity.this))
                    .params("password", oldpsw.getText().toString())
                    .params("newPassword", newpsw.getText().toString())
                    .execute(new GsonCallback<ChangePswBean>(ChangePswBean.class) {
                        @Override
                        public void onSuccess(Response<ChangePswBean> response) {
                            ChangePswBean body = response.body();
                            if (body.status.equals("200")) {
                                Toast.makeText(ChangeActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChangeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (body.status.equals("501")) {
                                Toast.makeText(ChangeActivity.this, "账号或密码错误~", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<ChangePswBean> response) {
                            super.onError(response);
                            Toast.makeText(ChangeActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                    });


        }
    }
}
