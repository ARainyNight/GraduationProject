package com.edu.shg_android.ui.activity.myorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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

public class MyUnshippedActivity extends BaseAppCompatActivity {

    private List<OrderJs.DataBean> orderList = new ArrayList<>();
    private OrderAdapter adapter ;
    private RecyclerView recyclerView;

    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("我未发货");

        baseApplication = (BaseApplication) this.getApplication();
        initView();
    }

    private void initView() {
        initRecyclerview();
    }

    private void initRecyclerview() {
        initOrder();

        recyclerView = (RecyclerView)findViewById(R.id.myunshipped_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initOrder() {
        LoginJs.UserBean userBean = baseApplication.getUser();
        int sellerid = userBean.getUid();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticClass.GetTradeForSellerid+"?sellerid="+sellerid)
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
        Gson gson= new Gson();
        OrderJs orderJs = gson.fromJson(json,new TypeToken<OrderJs>(){}.getType());
        orderList = orderJs.getData();
        adapter = new OrderAdapter(orderList);
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                OrderJs.DataBean orderDate = orderList.get(position);
                int tid = orderDate.getTid();
                goShipped(tid);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void goShipped(int tid) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticClass.SellerGoShipped+"?tid="+tid)
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(MyUnshippedActivity.this);
                            builder.setTitle("提示")
                                    .setMessage("发货成功！")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    });

                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_unshipped;
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
