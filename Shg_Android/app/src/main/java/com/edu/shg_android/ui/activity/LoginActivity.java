package com.edu.shg_android.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.shg_android.MainActivity;
import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.ShareUtils;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.utils.UtilTools;
import com.edu.shg_android.view.CustomDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginActivity_username_et;
    private EditText loginActivity_password_et;
    private CheckBox loginActivity_rememberpsd_ck;
    private Button loginActivity_btn;
    private TextView loginActivity_forgetpsd_tv;
    private TextView loginActivity_new_user_register_tv;

    private CustomDialog dialog = null;

    private boolean isRememberpsd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCollectorUtil.addActivity(this);

        initView();

        loginActivity_btn.setOnClickListener(this);
        loginActivity_new_user_register_tv.setOnClickListener(this);
        loginActivity_forgetpsd_tv.setOnClickListener(this);
    }

    private void initView() {
        loginActivity_username_et = (EditText) findViewById(R.id.loginActivity_username_et);
        loginActivity_password_et = (EditText) findViewById(R.id.loginActivity_password_et);
        loginActivity_rememberpsd_ck = (CheckBox) findViewById(R.id.loginActivity_rememberpsd_ck);
        loginActivity_btn = (Button) findViewById(R.id.loginActivity_btn);
        loginActivity_forgetpsd_tv = (TextView) findViewById(R.id.loginActivity_forgetpsd_tv);
        loginActivity_new_user_register_tv = (TextView) findViewById(R.id.loginActivity_new_user_register_tv);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);


        isRememberpsd = ShareUtils.getBoolean(this, "RememberPsd", false);
        loginActivity_rememberpsd_ck.setChecked(isRememberpsd);
        if (isRememberpsd) {
            loginActivity_username_et.setText(ShareUtils.getString(this, "name", null));
            loginActivity_password_et.setText(ShareUtils.getString(this, "psd", null));
        } else {
            loginActivity_username_et.setText("");
            loginActivity_password_et.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginActivity_btn:
                //登录
                login();
                break;
            case R.id.loginActivity_new_user_register_tv:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.loginActivity_forgetpsd_tv:
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
                break;
        }
    }

    private void login() {
        final BaseApplication baseApplication = (BaseApplication) this.getApplication();
        if (loginActivity_rememberpsd_ck.isChecked()) {
            ShareUtils.putString(LoginActivity.this, "login_name", loginActivity_username_et.getText().toString());
            ShareUtils.putString(LoginActivity.this, "login_psd", loginActivity_password_et.getText().toString());
            ShareUtils.putBoolean(LoginActivity.this, "isRememberpsd", loginActivity_rememberpsd_ck.isChecked());
        }
        String name = loginActivity_username_et.getText().toString().trim();
        String psd = loginActivity_password_et.getText().toString().trim();
        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(psd)) {
            dialog.show();
            //拿到okhttpClient对象
            OkHttpClient okHttpClient = new OkHttpClient();
            //构造Request
            Request.Builder builder = new Request.Builder();
            L.d("url = " + StaticClass.loginUrl + "?username=" + name + "&password=" + psd);
            Request request = builder.get().url(StaticClass.loginUrl + "?username=" + name + "&password=" + psd).build();
            //将Request封装为Call
            Call call = okHttpClient.newCall(request);

            //执行call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    dialog.dismiss();
                    L.e("onFailure" + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    L.d("res=" + res);
                    Gson gson = new Gson();
                    dialog.dismiss();
//                    res = res.substring(1,res.length()-1);
                    L.d("new_res=" + res);
                    List<LoginJs> loginJss = gson.fromJson(res, new TypeToken<List<LoginJs>>() {
                    }.getType());
                    LoginJs loginJs = loginJss.get(0);
                    L.d("loginJS=" + loginJs);
                    if (loginJs.getMsg().equals("登陆成功!")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        baseApplication.setUser(loginJs.getUser());
                        startActivity(intent);
                        finish();
                    } else if (loginJs.getMsg().equals("登录失败!")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UtilTools.Dialog(LoginActivity.this, "密码错误");
                                loginActivity_password_et.setText("");
                            }
                        });
                    }
                }
            });
        } else {
            UtilTools.Dialog(LoginActivity.this, "输入框不能为空");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);

        ShareUtils.putBoolean(this, "RememberPsd", loginActivity_rememberpsd_ck.isChecked());
        if (loginActivity_rememberpsd_ck.isChecked()) {
            ShareUtils.putString(this, "name", loginActivity_username_et.getText().toString());
            ShareUtils.putString(this, "psd", loginActivity_password_et.getText().toString());
        } else {
            ShareUtils.deleShare(this, "name");
            ShareUtils.deleShare(this, "psd");
        }
    }
}
