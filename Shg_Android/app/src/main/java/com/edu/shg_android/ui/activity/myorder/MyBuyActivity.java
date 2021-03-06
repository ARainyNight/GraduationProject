package com.edu.shg_android.ui.activity.myorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.edu.shg_android.R;
import com.edu.shg_android.adapter.OrderAdapter;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.json.OrderJs;
import com.edu.shg_android.ui.activity.BaseAppCompatActivity;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.StaticClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyBuyActivity extends BaseAppCompatActivity {

    private List<OrderJs.DataBean> orderList = new ArrayList<>();
    private OrderAdapter adapter;
    private RecyclerView recyclerView;

    private BaseApplication baseApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("我购买的");

        baseApplication = (BaseApplication) this.getApplication();
        initView();
    }

    private void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        initOrder();

        recyclerView = (RecyclerView) findViewById(R.id.my_but_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initOrder() {
        LoginJs.UserBean userBean = baseApplication.getUser();
        int buyid = userBean.getUid();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticClass.GetTradeForBuyid + "?buyid=" + buyid)
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jsonToEntity(json);
                    }
                });
            }
        });

    }

    private void jsonToEntity(String json) {

        Gson gson = new Gson();
        OrderJs orderJs = gson.fromJson(json, new TypeToken<OrderJs>() {
        }.getType());
        orderList = orderJs.getData();
        adapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_buy;
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
