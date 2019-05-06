package com.edu.shg_android.utils.http.listener;

/**
 * Created by lin on 2019/5/6.
 * 描述:
 */
public class DisposeDataHandle {
    public DisposeDataListener listener = null;

    public Class<?> clazz = null;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.listener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.listener = listener;
        this.clazz = clazz;
    }
}
