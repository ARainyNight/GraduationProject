package com.nuc.shg.dto;

/***
 *  ClassName : Ok
 *  Author    : lin
 *  Date      : 2019/4/16 16:10    
 *  Remark    : 
 */

public class Ok {

    private int code ;
    private String msg ;

    public Ok(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Ok(int code) {
        this.code = code;
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
}
