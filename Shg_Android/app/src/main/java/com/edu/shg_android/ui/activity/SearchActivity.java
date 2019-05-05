package com.edu.shg_android.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edu.shg_android.R;
import com.edu.shg_android.adapter.CommodityAdapter;
import com.edu.shg_android.json.CommodityJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
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

public class SearchActivity extends BaseAppCompatActivity {

    private List<CommodityJs.DataBean> commodityList = new ArrayList<>();
    private CommodityAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("搜索结果");

        initView();
    }

    private void initView() {

        initRecyclerView();

    }

    private void initRecyclerView() {

        initSeach();
        recyclerView =(RecyclerView)findViewById(R.id.search_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initSeach() {
        OkHttpClient client  = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(StaticClass.Search+"?cname="+getIntent().getStringExtra("searchname"))
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("请求调用失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String json = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jsonToEntity(json);
                        }
                    });
                }
            }
        });
    }

    private void jsonToEntity(String json) {
        Gson gson = new Gson();
        CommodityJs commodityJs = gson.fromJson(json,new TypeToken<CommodityJs>(){}.getType());

        commodityList = commodityJs.getData();

        adapter = new CommodityAdapter(commodityList);
        adapter.setOnItemClickListener(new CommodityAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                CommodityJs.DataBean commodityJs1 = commodityList.get(position);
                Intent intent= new Intent(SearchActivity.this, CommodityShowActivity.class);
                L.d("+++++++++++++"+commodityJs1.getCname());
                intent.putExtra("name",commodityJs1.getCname());
                intent.putExtra("price",commodityJs1.getCprice());
                intent.putExtra("category",commodityJs1.getCategory());
                intent.putExtra("username",commodityJs1.getUser().getUname());
                intent.putExtra("userpun",commodityJs1.getUser().getUpnum());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
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
