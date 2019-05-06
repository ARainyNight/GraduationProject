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
import com.edu.shg_android.json.OkJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
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
 * 忘记密码页面
 */
public class ForgetPasswordActivity extends BaseAppCompatActivity {

    private EditText forget_name_et;
    private EditText forget_num_et;
    private EditText forget_psd_et;
    private EditText forget_psd_again;
    private Button forget_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);

        initView();
        getToolbarTitle().setText("忘记密码");
    }

    private void initView() {
        forget_name_et = (EditText) findViewById(R.id.forget_name_et);
        forget_num_et = (EditText) findViewById(R.id.forget_num_et);
        forget_psd_et = (EditText) findViewById(R.id.forget_psd_et);
        forget_psd_again = (EditText) findViewById(R.id.forget_psd_again);
        forget_btn = (Button) findViewById(R.id.forget_btn);

        forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forget();
            }
        });
    }

    private void forget() {
        String name = forget_name_et.getText().toString().trim();
        String num = forget_num_et.getText().toString().trim();
        String psd = forget_psd_et.getText().toString().trim();
        String psd_again = forget_psd_again.getText().toString().trim();

        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(num) & !TextUtils.isEmpty(psd) & !TextUtils.isEmpty(psd_again)) {
            if (psd.equals(psd_again)) {
                OkHttpClient httpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get()
                        .url(StaticClass.ForgetPassword+"?name="+name+"&num="+num+"&password="+psd)
                        .build();
                Call call = httpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ForgetPasswordActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        Gson gson = new Gson();
                        OkJs okJs = gson.fromJson(res,new TypeToken<OkJs>(){}.getType());
                        if (okJs.getCode()==0){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UtilTools.Dialog(ForgetPasswordActivity.this,"用户不存在!");
                                }
                            });
                        }else if (okJs.getCode() ==2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UtilTools.Dialog(ForgetPasswordActivity.this,"手机号错误!");
                                }
                            });
                        }else if (okJs.getCode() ==1){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ForgetPasswordActivity.this,"密码找回成功",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                                    finish();
                                }
                            });
                        }
                    }
                });
            } else {
                UtilTools.Dialog(this, "两次密码不一致");
                forget_psd_et.setText("");
                forget_psd_again.setText("");
            }
        } else {
            UtilTools.Dialog(this, "输入框不能为空！");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
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
