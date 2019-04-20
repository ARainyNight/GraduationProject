package com.edu.shg_android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.shg_android.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class MySelfFragment extends Fragment {

    private CircleImageView my_img;
    private TextView my_uname_et;
    private TextView my_uphone_et;
    private LinearLayout my_location_layout;
    private LinearLayout my_order_layout;
    private LinearLayout my_release_layout;
    private LinearLayout my_setting_layout;

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

    }
}
