package com.edu.shg_android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.utils.ActivityCollectorUtil;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.view.WheelViewDialog;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.util.ArrayList;
import java.util.List;

public class ReleaseActivity extends BaseAppCompatActivity {

    private Button choose_btn;
    private TextView choose_tv;
    private ArrayList<String> stringArrayList;

    private WheelViewDialog dialog;

    private List<String> photos;
    private BaseAdapter adapter;
    private int reqCode = 12;

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
        choose_tv =(TextView) findViewById(R.id.choose_tv);

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
