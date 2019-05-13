package com.edu.shg_android.application;

import android.support.multidex.MultiDexApplication;

import com.edu.shg_android.json.LoginJs;
import com.edu.shg_android.utils.http.CommonOkHttpClient;

import cn.jpush.android.api.JPushInterface;


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
        CommonOkHttpClient.initClient();
        JPushInterface.init(this);
    }


    private LoginJs.UserBean user;

    public LoginJs.UserBean getUser() {
        return user;
    }

    public void setUser(LoginJs.UserBean user) {
        this.user = user;
    }
}
