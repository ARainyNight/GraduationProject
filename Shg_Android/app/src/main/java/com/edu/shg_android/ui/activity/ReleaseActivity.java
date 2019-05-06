package com.edu.shg_android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.utils.UtilTools;
import com.edu.shg_android.utils.http.CommonOkHttpClient;
import com.edu.shg_android.utils.http.exception.OkHttpException;
import com.edu.shg_android.utils.http.listener.DisposeDataHandle;
import com.edu.shg_android.utils.http.listener.DisposeDataListener;
import com.edu.shg_android.view.WheelViewDialog;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 商品发布页
 */
public class ReleaseActivity extends BaseAppCompatActivity {

    private Button choose_btn;
    private TextView choose_tv;
    private ArrayList<String> stringArrayList;

    private WheelViewDialog dialog;

    private List<String> photos;
    private BaseAdapter adapter;
    private int reqCode = 12;

    private EditText commodityName;
    private EditText commodityPrice;

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        getToolbarTitle().setText("发布商品");
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release;
    }


    private void initView() {
        //初始化选择Dialog
        initDialog();
        //初始化图片选择器
        initSelectImg();

        commodityName = (EditText) findViewById(R.id.release_commodity_name);
        commodityPrice = (EditText) findViewById(R.id.release_commodity_price);

        BaseApplication baseApplication = (BaseApplication) this.getApplication();
        LoginJs.UserBean userBean = baseApplication.getUser();
        final int userid = userBean.getUid();
        findViewById(R.id.upload_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commoName = commodityName.getText().toString();
                String commoPrice = commodityPrice.getText().toString();
                String category = choose_tv.getText().toString();
                if (!TextUtils.isEmpty(commoName) & !TextUtils.isEmpty(commoPrice) & !TextUtils.isEmpty(category)) {
                    if (photos==null) {
                        UtilTools.Dialog(ReleaseActivity.this, "请选择图片");
                    } else {
                        File file = new File(photos.get(0));
                        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("upload_file", file.getName(), fileBody)
                                .addFormDataPart("commoName", commoName)
                                .addFormDataPart("commoPrice", commoPrice)
                                .addFormDataPart("category", category)
                                .addFormDataPart("uid", userid + "")
                                .build();
                        CommonOkHttpClient.uploadFile(new File(photos.get(0)), requestBody, StaticClass.FileLoad, new DisposeDataHandle(new DisposeDataListener() {
                            @Override
                            public void onSuccess(String responseStr) {
                                L.d(responseStr + "++++++++");
                                Toast.makeText(ReleaseActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(OkHttpException e) {
                                Toast.makeText(ReleaseActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                            }
                        }));
                    }
                } else {
                    UtilTools.Dialog(ReleaseActivity.this, "输入框不能为空");
                }

            }
        });
    }

    private void initSelectImg() {
        Button btn = (Button) findViewById(R.id.Add_Img_btn);
        GridView gv = (GridView) findViewById(R.id.img_gv);
        gv.setAdapter(adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return photos == null ? 0 : photos.size();
            }

            @Override
            public Object getItem(int position) {
                if (photos == null) {
                    return null;
                }
                return photos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView view = new ImageView(ReleaseActivity.this);
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                view.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        256));
                String path = (String) getItem(position);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
                opts.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeFile(path, opts);
                view.setImageBitmap(bitmap);
                return view;
            }
        });

        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册
                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(1)
                        .singlePhoto(false)
                        .hintOfPick("最多选择1张")
                        .filterMimeTypes(new String[]{})
                        .build();
                GalleryActivity.openActivity(ReleaseActivity.this, reqCode, config);
            }
        });

    }

    private void initDialog() {
        choose_btn = (Button) findViewById(R.id.choose_btn);
        choose_tv = (TextView) findViewById(R.id.choose_tv);

        stringArrayList = StaticClass.getCategory();
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        dialog = new WheelViewDialog(ReleaseActivity.this, stringArrayList);
        dialog.setOnSelectedListener(new WheelViewDialog.OnSelectedListener() {
            @Override
            public void getData(String data) {
                choose_tv.setText(data);
            }
        });
        dialog.show();

    }


    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @SuppressWarnings("all")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (12 == requestCode && resultCode == Activity.RESULT_OK) {
            //照片路径集合返回值
            photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
