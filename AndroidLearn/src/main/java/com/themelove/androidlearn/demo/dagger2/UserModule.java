package com.themelove.androidlearn.demo.dagger2;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
@Module
public class UserModule {

    private Context mContext;

    public UserModule(Context context){
        this.mContext=context;
    }

    @Provides
    public String provideName(){
        return "userStore";
    }

    @Provides
    public ApiService provideApiService(OkHttpClient okHttpClient){
        return new ApiService(this.mContext, okHttpClient);
    }

    @Provides
    public UserStore provideUserStore(String name){
        return new UserStore(name);
    }

    @Provides
    public UserManager proviceUserManager(ApiService apiService, UserStore userStore){
        return new UserManager(apiService, userStore);
    }
}
