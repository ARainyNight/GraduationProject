package com.edu.shg_android.utils.http.exception;

/**
 * Created by lin on 2019/5/6.
 * 描述:
 */
public class OkHttpException extends Exception {

    private static final long serialVersionUID = 1L;

    private int status;

    private Object msg;

    public OkHttpException(int status, Object msg){
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}

