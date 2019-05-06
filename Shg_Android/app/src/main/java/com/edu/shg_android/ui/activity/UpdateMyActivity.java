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
 * 修改个人信息页面
 */
public class UpdateMyActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private EditText my_name_et;
    private EditText my_password_et;
    private EditText my_num_et;
    private Button my_update_btn;
    private Button my_save_btn;

    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);

        initView();
        setEnable(false);
        getToolbarTitle().setText("修改个人信息");


    }

    private void initView() {
        my_name_et = findViewById(R.id.my_name_et);
        my_password_et = findViewById(R.id.my_password_et);
        my_num_et = findViewById(R.id.my_num_et);
        my_update_btn = findViewById(R.id.my_update_btn);
        my_save_btn = findViewById(R.id.my_save_btn);

        baseApplication = (BaseApplication)this.getApplication();
        LoginJs.UserBean userBean = baseApplication.getUser();
        my_name_et.setText(userBean.getUname());
        my_password_et.setText(userBean.getUpassword());
        my_num_et.setText(userBean.getUpnum());

        my_update_btn.setOnClickListener(this);
        my_save_btn.setOnClickListener(this);
    }

    //控制焦点
    public void setEnable(boolean enable) {
        my_name_et.setEnabled(enable);
        my_password_et.setEnabled(enable);
        my_num_et.setEnabled(enable);
        if (enable) {
            //可见
            my_save_btn.setVisibility(View.VISIBLE);
        } else {
            //不可见
            my_save_btn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_my;
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
            case R.id.my_update_btn:
                setEnable(true);
                break;
            case R.id.my_save_btn:
                setEnable(false);
                String newName = my_name_et.getText().toString().trim();
                String newPsd = my_password_et.getText().toString().trim();
                String newNum = my_num_et.getText().toString().trim();
                LoginJs.UserBean userBean = baseApplication.getUser();
                int id = userBean.getUid();

                //更改数据库中的user信息
                updateMy(id, newName, newPsd, newNum);
                break;
        }
    }

    private void updateMy(int id, final String newName, final String newPsd, final String newNum) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticClass.UserUpdateMy + "?id=" + id + "&name=" + newName + "&password=" + newPsd + "&num=" + newNum)
                .get().build();

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
                if (okJs.getCode()==1){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilTools.Dialog(UpdateMyActivity.this,"更新成功");

                            //更新全局变量中的user
                            LoginJs.UserBean userBean=baseApplication.getUser();
                            userBean.setUname(newName);
                            userBean.setUpassword(newPsd);
                            userBean.setUpnum(newNum);
                            baseApplication.setUser(userBean);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilTools.Dialog(UpdateMyActivity.this,"更新失败!");
                        }
                    });
                }
            }
        });


    }
}
