package com.edu.shg_android.ui.activity.scan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.OkJs;
import com.edu.shg_android.json.ScanJs;
import com.edu.shg_android.ui.activity.BaseAppCompatActivity;
import com.edu.shg_android.ui.activity.ReleaseActivity;
import com.edu.shg_android.ui.activity.myorder.MyUnshippedActivity;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.utils.UtilTools;
import com.edu.shg_android.view.WheelViewDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.show.api.ShowApiRequest;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScanAddActivity extends BaseAppCompatActivity {

    private Button choose_btn;
    private TextView choose_tv;
    private ArrayList<String> stringArrayList;

    private WheelViewDialog dialog;

    private EditText scan_name_et;
    private EditText scan_price_et;
    private ImageView scan_img;

    private Button scan_release_btn;

    private Button zxing_btn;
    private String result;

    protected Handler mHandler = new Handler();

    private BaseApplication baseApplication;
    private String cimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.removeActivity(this);
        getToolbarTitle().setText("发布商品");

        initView();
        setEnable(false);
    }

    private void initView() {
        zxing_btn = (Button) findViewById(R.id.zxing_btn);
        scan_name_et = (EditText) findViewById(R.id.scan_name_et);
        scan_price_et = (EditText) findViewById(R.id.scan_price_et);
        scan_img = (ImageView) findViewById(R.id.scan_img);
        scan_release_btn = (Button) findViewById(R.id.scan_release_btn);
        baseApplication = (BaseApplication) this.getApplication();
        scan_img.setImageResource(R.drawable.nophoto);


        //扫描条形码
        initZxing();

        //初始化选择Dialog
        initDialog();


        scan_release_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cuid = baseApplication.getUser().getUid();
                String cname = scan_name_et.getText().toString();
                String cprice = scan_price_et.getText().toString();
                String category = choose_tv.getText().toString();
                if (!TextUtils.isEmpty(cname) & !TextUtils.isEmpty(cprice) & !TextUtils.isEmpty(category)) {
                    OkHttpClient httpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    L.d("========================发布运行中===========" + cimg);
                    Request request = builder.get().url(StaticClass.ScanAddCommodity + "?cname=" + cname +
                            "&cprice=" + cprice + "&cuid=" + cuid + "&category=" + category + "&cimg=" + cimg).build();

                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String res = response.body().string();
                            L.d("==============" + res);
                            Gson gson = new Gson();
                            OkJs okJs = gson.fromJson(res, new TypeToken<OkJs>() {
                            }.getType());
                            if (okJs.getCode() == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(ScanAddActivity.this);
                                        builder.setTitle("提示")
                                                .setMessage("发布成功！")
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
                } else {
                    UtilTools.Dialog(ScanAddActivity.this, "输入框不能为空");
                }
            }
        });
    }

    private void initZxing() {
        zxing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(ScanAddActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描条形码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            result = scanResult.getContents();
            System.out.println(result);

            new Thread() {
                @Override
                public void run() {
                    L.d("===================开始=="+result);
                    System.out.println("===================================1"+result);
                    String appid = "94826";
                    String secret = "4e3d31fa72014af3bee349a1a08d2dba";
                    final String res = new ShowApiRequest("http://route.showapi.com/66-22", appid, secret)
                            .addTextPara("code", result)
                            .post();
                    mHandler.post(new Thread() {
                        @Override
                        public void run() {
                            setEnable(true);
                            Gson gson = new Gson();
                            ScanJs scanJs = gson.fromJson(res, new TypeToken<ScanJs>() {
                            }.getType());
                            System.out.println("===================================1"+scanJs);
                            scan_name_et.setText(scanJs.getShowapi_res_body().getGoodsName());
                            scan_price_et.setText(scanJs.getShowapi_res_body().getPrice());
                            cimg = scanJs.getShowapi_res_body().getImg();
                            L.d("===================结束=="+result);
                            System.out.println("===================================3"+result);
                            L.d("=" + cimg + "=");
                            if (!cimg.equals("")) {
                                System.out.println(true);
                                L.d("=" + cimg + "=");
                                Picasso.with(ScanAddActivity.this).load(cimg).into(scan_img);
                            } else {
                                scan_img.setImageResource(R.drawable.nophoto);
                            }

                        }
                    });
                }
            }.start();

        }
    }

    private void initDialog() {
        choose_btn = (Button) findViewById(R.id.choose_scan_btn);
        choose_tv = (TextView) findViewById(R.id.choose_scan_tv);
        stringArrayList = StaticClass.getCategory();
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        dialog = new WheelViewDialog(ScanAddActivity.this, stringArrayList);
        dialog.setOnSelectedListener(new WheelViewDialog.OnSelectedListener() {
            @Override
            public void getData(String data) {
                choose_tv.setText(data);
            }
        });
        dialog.show();
    }


    //控制焦点
    public void setEnable(boolean enable) {
        if (enable) {
            scan_name_et.setVisibility(View.VISIBLE);
            scan_price_et.setVisibility(View.VISIBLE);
            scan_img.setVisibility(View.VISIBLE);
            scan_release_btn.setVisibility(View.VISIBLE);
            choose_btn.setVisibility(View.VISIBLE);
            choose_tv.setVisibility(View.VISIBLE);
        } else {
            scan_name_et.setVisibility(View.INVISIBLE);
            scan_price_et.setVisibility(View.INVISIBLE);
            scan_img.setVisibility(View.INVISIBLE);
            scan_release_btn.setVisibility(View.INVISIBLE);
            choose_btn.setVisibility(View.INVISIBLE);
            choose_tv.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_add;
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
