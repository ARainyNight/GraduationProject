package com.edu.shg_android.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.application.BaseApplication;
import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.ui.activity.MyLocationActivity;
import com.edu.shg_android.ui.activity.MyOrderActivity;
import com.edu.shg_android.ui.activity.MyReleaseActivity;
import com.edu.shg_android.ui.activity.MySettingActivity;
import com.edu.shg_android.utils.L;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.utils.UtilTools;
import com.edu.shg_android.view.CustomDialog;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class MySelfFragment extends Fragment implements View.OnClickListener {

    //圆形头像
    private CircleImageView my_img;

    private TextView my_uname_et;
    private TextView my_uphone_et;
    private LinearLayout my_location_layout;
    private LinearLayout my_order_layout;
    private LinearLayout my_release_layout;
    private LinearLayout my_setting_layout;

    private BaseApplication application;

    private CustomDialog customDialog;
    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        my_img = (CircleImageView)view.findViewById(R.id.my_img);
        my_uname_et = (TextView)view.findViewById(R.id.my_uname_et);
        my_uphone_et =(TextView)view.findViewById(R.id.my_uphone_et);
        my_location_layout =(LinearLayout)view.findViewById(R.id.my_location_layout);
        my_order_layout =(LinearLayout)view.findViewById(R.id.my_order_layout);
        my_release_layout =(LinearLayout)view.findViewById(R.id.my_release_layout);
        my_setting_layout =(LinearLayout)view.findViewById(R.id.my_setting_layout);

        application = (BaseApplication) getActivity().getApplication();
        LoginJs.UserBean userBean = application.getUser();
        my_uname_et.setText(userBean.getUname());
        my_uphone_et.setText(userBean.getUpnum());

        my_img.setOnClickListener(this);
        UtilTools.getImageToShare(getActivity(),my_img);
        customDialog= new CustomDialog(getActivity(),100,100,R.layout.dialog_photo,R.style.Theme_dialog, Gravity.BOTTOM,R.style.pop_anim_style);
        customDialog.setCancelable(false);
        //customDialog.findViewById(R.id.btn_camera).setOnClickListener(this);
        customDialog.findViewById(R.id.btn_picture).setOnClickListener(this);
        customDialog.findViewById(R.id.btn_cancel).setOnClickListener(this);

        my_location_layout.setOnClickListener(this);
        my_order_layout.setOnClickListener(this);
        my_release_layout.setOnClickListener(this);
        my_setting_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_location_layout:
                //我的地址
                startActivity(new Intent(getActivity(), MyLocationActivity.class));
                break;
            case R.id.my_order_layout:
                //我的订单
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.my_release_layout:
                //我的发布
                startActivity(new Intent(getActivity(), MyReleaseActivity.class));
                break;
            case R.id.my_setting_layout:
                //设置
                startActivity(new Intent(getActivity(), MySettingActivity.class));
                break;
            case R.id.my_img:
                //点击圆形头像
                customDialog.show();
                break;
//            case R.id.btn_camera:
//                //跳转相机
//                toCamera();
//                break;
            case R.id.btn_picture:
                //跳转图库
                toPicture();
                break;
            case R.id.btn_cancel:
                customDialog.dismiss();
                break;
        }
    }

    private Uri imageUri = null;
    private Uri outputUri;//裁剪完照片保存地址

    private File tempFile = null;

    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, StaticClass.IMAGE_REQUEST_CODE);
        customDialog.dismiss();
    }

    private void toCamera() {
        File outputImage = new File(getContext().getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(getActivity(), "com.edu.shg_android.provider", outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, StaticClass.CAMERA_REQUEST_CODE);
        customDialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                case StaticClass.IMAGE_REQUEST_CODE:
                    //相册的数据
                    startPhotoZoom(data.getData());
                    break;
                case StaticClass.CAMERA_REQUEST_CODE:
                    //相机的数据
//                    tempFile =new File(Environment.getExternalStorageDirectory(),StaticClass.PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(imageUri);
                    break;
                case StaticClass.RESULT_REQUEST_CODE:
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }


    //裁剪图片的方法
    private void startPhotoZoom(Uri uri) {
        File file = new File(getContext().getExternalCacheDir(), "crop_image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        startActivityForResult(intent, StaticClass.RESULT_REQUEST_CODE);
    }

    //设置头像
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            my_img.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        UtilTools.putImageToShare(getActivity(), my_img);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //保存
        UtilTools.putImageToShare(getActivity(), my_img);
        L.i("MyFragment onDestroy执行");
    }

}
