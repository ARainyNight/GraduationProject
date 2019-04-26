package com.edu.shg_android.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.shg_android.R;
import com.edu.shg_android.json.RegisterJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.utils.UtilTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private EditText registerActivity_username_et;
    private EditText registerActivity_password_et;
    private EditText registerActivity_password_et_again;
    private EditText registerActivity_address_et;
    private EditText registerActivity_pnum_et;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);

        initView();
        getToolbarTitle().setText("注册");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    private void initView() {

        registerActivity_username_et = (EditText) findViewById(R.id.registerActivity_username_et);
        registerActivity_password_et = (EditText) findViewById(R.id.registerActivity_password_et);
        registerActivity_password_et_again = (EditText) findViewById(R.id.registerActivity_password_et_again);
        registerActivity_address_et = (EditText) findViewById(R.id.registerActivity_address_et);
        registerActivity_pnum_et = (EditText) findViewById(R.id.registerActivity_pnum_et);
        register_btn = (Button) findViewById(R.id.register_btn);

        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                //注册
                register();
                break;
        }
    }

    private void register() {
        String name = registerActivity_username_et.getText().toString().trim();
        String psd = registerActivity_password_et.getText().toString().trim();
        String psd_again = registerActivity_password_et_again.getText().toString().trim();
        String address = registerActivity_address_et.getText().toString().trim();
        String pnum = registerActivity_pnum_et.getText().toString().trim();

        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(psd) & !TextUtils.isEmpty(psd_again)
                & !TextUtils.isEmpty(address) & !TextUtils.isEmpty(pnum)) {
            if (psd.equals(psd_again)) {
                OkHttpClient httpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url(StaticClass.RegisterUrl + "?username=" + name +
                        "&password=" + psd + "&address=" + address + "&pnum=" + pnum).build();
                Call call = httpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        L.d("res1="+res);
                        Gson gson = new Gson();
                        RegisterJs registerJs = gson.fromJson(res, new TypeToken<RegisterJs>() {
                        }.getType());
                        if (registerJs.getCode() == 1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        } else if (registerJs.getCode() == 2) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UtilTools.Dialog(RegisterActivity.this, "用户名已存在，请重新注册!");
                                    registerActivity_username_et.setText("");
                                    registerActivity_password_et.setText("");
                                    registerActivity_password_et_again.setText("");
                                    registerActivity_address_et.setText("");
                                    registerActivity_pnum_et.setText("");
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            } else {
                UtilTools.Dialog(this, "两次输入的密码不一致");
            }
        } else {
            UtilTools.Dialog(this, "输入框不能为空");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
