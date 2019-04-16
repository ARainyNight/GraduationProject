package com.edu.shg_android.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edu.shg_android.R;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.view.WheelViewDialog;

import java.util.ArrayList;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class ReleaseFragment extends Fragment {

    private Button choose_btn;
    private TextView choose_tv;
    private ArrayList<String> stringArrayList;

    private WheelViewDialog dialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_release, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {

        //初始化选择Dialog
        initDialog(view);
    }

    private void initDialog(View view) {

        choose_btn = view.findViewById(R.id.choose_btn);
        choose_tv = view.findViewById(R.id.choose_tv);

        stringArrayList = StaticClass.getCategory();
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        dialog = new WheelViewDialog(getActivity(), stringArrayList);
        dialog.setOnSelectedListener(new WheelViewDialog.OnSelectedListener() {
            @Override
            public void getData(String data) {
                choose_tv.setText(data);
            }
        });
        dialog.show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
