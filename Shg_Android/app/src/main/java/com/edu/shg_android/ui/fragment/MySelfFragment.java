package com.edu.shg_android.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.edu.shg_android.ui.activity.MyReleaseActivity;
import com.edu.shg_android.ui.activity.MySettingActivity;
import com.edu.shg_android.view.CustomDialog;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class MySelfFragment extends Fragment implements View.OnClickListener {

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
                break;
            case R.id.my_release_layout:
                //我的发布
                startActivity(new Intent(getActivity(), MyReleaseActivity.class));
                break;
            case R.id.my_setting_layout:
                //设置
                startActivity(new Intent(getActivity(), MySettingActivity.class));
                break;
        }
    }
}
