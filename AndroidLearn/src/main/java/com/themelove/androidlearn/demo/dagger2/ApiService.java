package com.themelove.androidlearn.demo.dagger2;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
public class ApiService {
    public final String TAG= ApiService.class.getSimpleName();
    private Context context;
    private OkHttpClient mOkHttpClient;

    @Inject
    public ApiService(Context context,OkHttpClient okHttpClient){
        this.context = context;
        this.mOkHttpClient = okHttpClient;
    }

    public void register(String url){
        Log.i(TAG,"register=====>okHttpClient=====>");
        mOkHttpClient.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG,"onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG,"onResponse");
            }
        });
    }
}
