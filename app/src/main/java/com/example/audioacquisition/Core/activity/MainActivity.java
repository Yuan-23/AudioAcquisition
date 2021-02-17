package com.example.audioacquisition.Core.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.audioacquisition.Core.fragment.FaxFragment;
import com.example.audioacquisition.Core.fragment.PracticeFragment;
import com.example.audioacquisition.Core.fragment.MineFragment;
import com.example.audioacquisition.Core.fragment.StudyFragment;
import com.example.audioacquisition.Core.fragment.TestFragment;
import com.example.audioacquisition.Core.helper.BottomNavigationViewHelper;
import com.example.audioacquisition.Core.helper.SharedPreferencesHelper;
import com.example.audioacquisition.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private int lastIndex;
    List<Fragment> mFragments;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        if (SharedPreferencesHelper.getLoginStatus(MainActivity.this).equals(false)) {
            Toast.makeText(MainActivity.this, "请在“我的”中进行登录哦", Toast.LENGTH_SHORT).show();
        }

        initBottomNavigation();
        initData();
    }


    public void initData() {

        mFragments = new ArrayList<>();
        mFragments.add(new StudyFragment());
        mFragments.add(new PracticeFragment());
        mFragments.add(new TestFragment());
        mFragments.add(new FaxFragment());
        mFragments.add(new MineFragment());
        // 初始化展示
        setFragmentPosition(0);
    }

    public void initBottomNavigation() {
        mBottomNavigationView = findViewById(R.id.bv_bottomNavigation);
        // 解决当item大于三个时，非平均布局问题
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        // 添加监听
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_study:
                        setFragmentPosition(0);
                        break;
                    case R.id.menu_practice:
                        setFragmentPosition(1);
                        break;
                    case R.id.menu_test:
                        setFragmentPosition(2);
                        break;
                    case R.id.menu_fax:
                        setFragmentPosition(3);
                        break;
                    case R.id.menu_mine:
                        setFragmentPosition(4);
                        break;
                    default:
                        break;
                }
                // 这里注意返回true,否则点击失效
                return true;
            }
        });
    }

    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.ll_frameLayout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }


}
