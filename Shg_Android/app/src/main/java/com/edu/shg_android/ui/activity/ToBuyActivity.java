package com.edu.shg_android.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.shg_android.MainActivity;
import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.json.OkJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 确认购买界面
 */
public class ToBuyActivity extends BaseAppCompatActivity {

    private int tcid;
    private int sellerid;
    private String sellername;
    private String sellerusername;
    private String sellerimg;
    private String sellerprice;
    private int buyid;
    private String buyname;
    private String buylocation;
    private String buynum;

    private TextView sellername_tv;
    private TextView sellerusername_tv;
    private ImageView sellerimg_img;
    private TextView sellerprice_tv;
    private TextView buyname_tv;
    private TextView buylocation_tv;
    private TextView buynum_tv;

    private Button buybtn;

    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("确认订单");
        initView();
    }

    private void initView() {
        tcid = getIntent().getIntExtra("cid", 0);
        sellerid = getIntent().getIntExtra("userid", 0);
        sellerimg = getIntent().getStringExtra("img");
        sellername = getIntent().getStringExtra("name");
        sellerusername = getIntent().getStringExtra("username");
        sellerprice = getIntent().getStringExtra("price");

        baseApplication = (BaseApplication) this.getApplication();
        LoginJs.UserBean userBean = baseApplication.getUser();
        buyid = userBean.getUid();
        buyname = userBean.getUname();
        buylocation = userBean.getUaddress();
        buynum = userBean.getUpnum();


        sellername_tv = (TextView) findViewById(R.id.seller_commo_name);
        sellerusername_tv = (TextView) findViewById(R.id.seller_name);
        sellerimg_img = (ImageView) findViewById(R.id.seller_img);
        sellerprice_tv = (TextView) findViewById(R.id.seller_price);
        buyname_tv = (TextView) findViewById(R.id.buy_name);
        buylocation_tv = (TextView) findViewById(R.id.buy_location);
        buynum_tv = (TextView) findViewById(R.id.buy_num);
        buybtn = (Button) findViewById(R.id.gobuy_btn);

        sellername_tv.setText(sellername);
        sellerusername_tv.setText(sellerusername);
        Picasso.with(ToBuyActivity.this).load(StaticClass.PhotoLoading + sellerimg).into(sellerimg_img);
        sellerprice_tv.setText(sellerprice);
        buyname_tv.setText(buyname);
        buylocation_tv.setText(buylocation);
        buynum_tv.setText(buynum);

        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //向订单表提交新的订单
                goaddTrade();
                //改变商品表中状态
                goUpdateCommodity();
                startActivity(new Intent(ToBuyActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void goUpdateCommodity() {
        OkHttpClient httpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.UpdateCommodityStatus + "?cid=" + tcid + "&status=2").build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                OkJs okjs = gson.fromJson(json, new TypeToken<OkJs>() {
                }.getType());
                if (okjs.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            L.d("修改商品表状态成功");
                        }
                    });
                }
            }
        });
    }

    private void goaddTrade() {
        OkHttpClient httpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(
                StaticClass.AddTrade + "?tcid=" + tcid + "&tcname=" + sellername
                        + "&tcprice=" + sellerprice + "&tcimg=" + sellerimg + "&buyid=" + buyid
                        + "&buyname=" + buyname + "&buyaddress=" + buylocation
                        + "&sellerid=" + sellerid + "&sellername=" + sellerusername
        ).build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("订单提交失败");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                OkJs okjs = gson.fromJson(json, new TypeToken<OkJs>() {
                }.getType());
                if (okjs.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ToBuyActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to_buy;
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
