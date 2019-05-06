package com.edu.shg_android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.json.CommodityJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.squareup.picasso.Picasso;

public class CommodityShowActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private TextView commo_name;
    private TextView commo_price;
    private TextView commo_category;
    private TextView commo_username;
    private TextView commo_userpun;
    private Button contact_btn;
    private Button buy_btn;
    private ImageView imgshow;


    private String name;
    private String price;
    private String category;
    private String username;
    private String userpun;
    private String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("商品详情页");

        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        category = getIntent().getStringExtra("category");
        username = getIntent().getStringExtra("username");
        userpun = getIntent().getStringExtra("userpun");
        img = getIntent().getStringExtra("img");
        initView();

    }

    private void initView() {
        commo_name = (TextView) findViewById(R.id.commo_name);
        commo_price = (TextView) findViewById(R.id.commo_price);
        commo_category = (TextView) findViewById(R.id.commo_category);
        commo_username = (TextView) findViewById(R.id.commo_username);
        commo_userpun = (TextView) findViewById(R.id.commo_userpun);
        contact_btn = (Button) findViewById(R.id.contact_btn);
        buy_btn = (Button) findViewById(R.id.buy_btn);
        imgshow = (ImageView) findViewById(R.id.commo_show_img);


        commo_name.setText(name);
        commo_price.setText(price);
        commo_category.setText(category);
        commo_username.setText(username);
        commo_userpun.setText(userpun);
        Picasso.with(this).load(StaticClass.PhotoLoading + img).into(imgshow);

        contact_btn.setOnClickListener(this);
        buy_btn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_show;
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
            case R.id.contact_btn:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + userpun));
                startActivity(intent);
                break;
            case R.id.buy_btn:
                break;
            default:
                break;
        }
    }
}
