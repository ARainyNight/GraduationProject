package com.edu.shg_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.shg_android.R;
import com.edu.shg_android.utils.ActivityCollectorUtil;

public class MyOrderActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("我的订单");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
