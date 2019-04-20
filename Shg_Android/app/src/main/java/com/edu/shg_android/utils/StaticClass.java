package com.edu.shg_android.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/4/9.
 * 描述: 数据常量封装
 */
public class StaticClass {

    public static ArrayList<String> list = new ArrayList<>();

    public static ArrayList<String> getCategory() {
        list.add("手机");
        list.add("图书");
        list.add("数码");
        list.add("服装鞋帽");
        list.add("电脑");
        list.add("电器");
        list.add("办公用品");
        list.add("其它");
        return list;
    }

    //登录url
    public static String loginUrl = "http://10.0.116.241:8080/user/androidUserLogin";
    //注册url
    public static String RegisterUrl = "http://10.0.116.241:8080/user/androidUserRegister";

}
