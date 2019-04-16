package com.edu.shg_android.application;

import android.support.multidex.MultiDexApplication;

/**
 * Created by lin on 2019/4/9.
 * 描述:
 */
public class BaseApplication extends MultiDexApplication {

    //创建
    @Override
    public void onCreate() {
        super.onCreate();

//        //初始化Bugly
//        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID,true );

    }
}
