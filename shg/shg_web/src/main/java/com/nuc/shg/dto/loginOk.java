package com.nuc.shg.dto;

import com.nuc.shg.entity.User;

/***
 *  ClassName : loginOk
 *  Author    : lin
 *  Date      : 2019/4/18 21:54    
 *  Remark    : 
 */

public class loginOk {

    private int code ;
    private String msg;
    private User user ;

    public loginOk(int code, String msg, User user) {
        this.code = code;
        this.msg = msg;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
