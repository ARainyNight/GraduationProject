package com.edu.shg_android.utils.http;

import com.edu.shg_android.utils.http.listener.DisposeDataHandle;
import com.edu.shg_android.utils.http.response.CommonJsonCallback;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by lin on 2019/5/6.
 * 描述:
 */
public class CommonOkHttpClient {

    private static final int TIMEOUT = 5;
    private static OkHttpClient client;

    public static void initClient(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);

        client = okHttpBuilder.build();
    }

    public static void uploadFile(File file, RequestBody body, String url, DisposeDataHandle handle){

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));

    }

}
