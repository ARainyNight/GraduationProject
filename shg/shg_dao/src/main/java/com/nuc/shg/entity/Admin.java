package com.nuc.shg.entity;

/***
 *  ClassName : Admin
 *  Author    : lin
 *  Date      : 2019/4/8 17:57    
 *  Remark    : 管理员
 */

public class Admin {

    private int aid;

    private String aname;

    private String apassword;

    public Admin(){

    }

    public Admin(String aname, String apassword) {
        this.aname = aname;
        this.apassword = apassword;
    }

    public Admin(int aid, String aname, String apassword) {
        this.aid = aid;
        this.aname = aname;
        this.apassword = apassword;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }
}
