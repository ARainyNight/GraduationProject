package com.edu.shg_android.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.edu.shg_android.R;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        if (mToolbarTitle != null) {
            mToolbarTitle.setText(getTitle());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }

    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }


    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }


    private void showBack() {
        getToolbar().setNavigationIcon(R.drawable.back2);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    protected boolean isShowBacking() {
        return true;
    }

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
