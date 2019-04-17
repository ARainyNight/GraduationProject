package com.nuc.shg.entity;

/***
 *  ClassName : User
 *  Author    : lin
 *  Date      : 2019/4/17 10:19    
 *  Remark    : 
 */

public class User {

    //用户ID
    private int uid;
    //用户姓名
    private String uname;
    //用户密码
    private String upassword;
    //用户地址
    private String uaddress;
    //用户电话
    private String upnum;
    //用户头像
    private String uimg ;

    public User(){

    }

    public User(String uname, String upassword, String uaddress, String upnum) {
        this.uname = uname;
        this.upassword = upassword;
        this.uaddress = uaddress;
        this.upnum = upnum;
    }

    public User(int uid, String uname, String upassword, String uaddress, String upnum) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uaddress = uaddress;
        this.upnum = upnum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUpnum() {
        return upnum;
    }

    public void setUpnum(String upnum) {
        this.upnum = upnum;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }
}
