package com.edu.shg_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.json.OkJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.utils.UtilTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 我的地址页面
 */
public class MyLocationActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private EditText mylocation_et;
    private Button mylocation_update_btn;
    private Button mylocation_save_btn;

    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);

        initView();
        setEnable(false);
        getToolbarTitle().setText("收货地址");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_location;
    }

    private void initView() {
        mylocation_et = (EditText) findViewById(R.id.mylocation_et);
        mylocation_update_btn = (Button) findViewById(R.id.mylocation_update_btn);
        mylocation_save_btn = (Button) findViewById(R.id.mylocation_save_btn);

        baseApplication = (BaseApplication) this.getApplication();
        mylocation_et.setText(baseApplication.getUser().getUaddress());

        mylocation_update_btn.setOnClickListener(this);
        mylocation_save_btn.setOnClickListener(this);
    }

    //控制焦点
    public void setEnable(boolean enable) {
        mylocation_et.setEnabled(enable);
        if (enable) {
            //可见
            mylocation_save_btn.setVisibility(View.VISIBLE);
        } else {
            //不可见
            mylocation_save_btn.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mylocation_update_btn:
                setEnable(true);
                break;
            case R.id.mylocation_save_btn:
                setEnable(false);
                String newLocation = mylocation_et.getText().toString().trim();


                LoginJs.UserBean userBean = baseApplication.getUser();
                int id = userBean.getUid();


                //更改数据库中的User地址
                updateLocation(id,newLocation);

                break;
        }
    }

    private void updateLocation(int id, final String newLocation) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticClass.UserUpdateLocation+"?id="+id+"&address="+newLocation)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("请求调用失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                OkJs okJs = gson.fromJson(res,new TypeToken<OkJs>(){}.getType());
                if (okJs.getCode() ==1){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilTools.Dialog(MyLocationActivity.this,"更新成功");
                            //更新全局变量中的user
                            LoginJs.UserBean userBean =baseApplication.getUser();
                            userBean.setUaddress(newLocation);
                            baseApplication.setUser(userBean);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilTools.Dialog(MyLocationActivity.this,"更新失败");
                        }
                    });
                }
            }
        });
    }

    //显示是否返回
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
