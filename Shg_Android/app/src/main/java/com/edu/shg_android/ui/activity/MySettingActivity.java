package com.edu.shg_android.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.utils.ActivityCollectorUtil;

public class MySettingActivity extends BaseAppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);

        getToolbarTitle().setText("我的设置");

        findViewById(R.id.update_my_btn).setOnClickListener(this);
        findViewById(R.id.my_exit).setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_setting;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.update_my_btn:
                startActivity(new Intent(MySettingActivity.this,UpdateMyActivity.class));
                break;
            case R.id.my_exit:
                exit();
                break;
        }
    }

    //用户退出
    private void exit() {
        startActivity(new Intent(MySettingActivity.this,LoginActivity.class));
        ActivityCollectorUtil.finishAllActivity();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
