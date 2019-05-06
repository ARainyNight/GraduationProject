package com.edu.shg_android.utils.http.listener;

import com.edu.shg_android.utils.http.exception.OkHttpException;

/**
 * Created by lin on 2019/5/6.
 * 描述:
 */
public interface DisposeDataListener {
    public void onSuccess(String responseStr);

    public void onFailure(OkHttpException e);
}
