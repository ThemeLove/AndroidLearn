package com.themelove.androidlearn.demo.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
@Module
public class OKHttpModule {

    @Provides
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }
}
