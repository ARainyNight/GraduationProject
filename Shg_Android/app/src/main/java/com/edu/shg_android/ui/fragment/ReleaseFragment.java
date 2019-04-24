package com.edu.shg_android.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.shg_android.MainActivity;
import com.edu.shg_android.R;
import com.edu.shg_android.ui.activity.ReleaseActivity;
import com.edu.shg_android.utils.StaticClass;
import com.edu.shg_android.view.WheelViewDialog;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class ReleaseFragment extends Fragment {

    private Button choose_btn;
    private TextView choose_tv;
    private ArrayList<String> stringArrayList;

    private WheelViewDialog dialog;

    private List<String> photos;
    private BaseAdapter adapter;
    private int reqCode = 12;


    @SuppressWarnings("all")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_release, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {

        Button btn = view.findViewById(R.id.release_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReleaseActivity.class));
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
