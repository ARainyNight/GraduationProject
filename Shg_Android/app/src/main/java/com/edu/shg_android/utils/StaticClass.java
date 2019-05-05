package com.edu.shg_android.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/4/9.
 * 描述: 数据常量封装
 */
public class StaticClass {

    public static final String PHOTO_IMAGE_FILE_NAME="fileImg.jpg";

    public static final int CAMERA_REQUEST_CODE =100 ;
    public static final int IMAGE_REQUEST_CODE =101 ;
    public static final int RESULT_REQUEST_CODE =102 ;

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


    //校园网IP
//    private static String ip ="10.0.116.241";
    //电脑局域网IP
    private static String ip="192.168.137.1";


    //登录url
    public static String loginUrl = "http://"+ip+":8080/user/androidUserLogin";
    //注册url
    public static String RegisterUrl = "http://"+ip+":8080/user/androidUserRegister";
    //用户修改地址url
    public static String UserUpdateLocation ="http://"+ip+":8080/user/androidUserUpdateAddress";
    //忘记密码url
    public static String ForgetPassword = "http://"+ip+":8080/user/androidUserForgetPassword";
    //用户修改信息url
    public static String UserUpdateMy="http://"+ip+":8080/user/androidUserUpdateMy";

    //加载商品信息
    public static String LoadingCommodity ="http://"+ip+":8080/commodity/auditPassJson";
    //加载我发布的商品信息
    public static String MyReleaseCommodity="http://"+ip+":8080/commodity/MyReleaseCommodity";
    //商品分类
    public static String SortCommodity ="http://"+ip+":8080/commodity/SortCommodity";
    //搜索url
    public static String Search = "http://"+ip+":8080/commodity/Search";
}
