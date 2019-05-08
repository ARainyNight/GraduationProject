package com.edu.shg_android.ui.activity.myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edu.shg_android.R;
import com.edu.shg_android.ui.activity.BaseAppCompatActivity;
import com.edu.shg_android.utils.ActivityCollectorUtil;

public class MyOrderActivity extends BaseAppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("我的订单");

        findViewById(R.id.my_buy_btn).setOnClickListener(this);
        findViewById(R.id.onWay_btn).setOnClickListener(this);
        findViewById(R.id.my_unshipped_btn).setOnClickListener(this);
        findViewById(R.id.my_ok_order_btn).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_buy_btn:
                startActivity(new Intent(MyOrderActivity.this, MyBuyActivity.class));
                break;
            case R.id.onWay_btn:
                startActivity(new Intent(MyOrderActivity.this,OnWayActivity.class));
                break;
            case R.id.my_unshipped_btn:
                startActivity(new Intent(MyOrderActivity.this,MyUnshippedActivity.class));
                break;
            case R.id.my_ok_order_btn:
                startActivity(new Intent(MyOrderActivity.this,MyOkOrderActivity.class));
                break;
        }
    }
}
