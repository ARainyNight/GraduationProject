package com.edu.shg_android.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.shg_android.R;

public class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_layout);
    }
}
