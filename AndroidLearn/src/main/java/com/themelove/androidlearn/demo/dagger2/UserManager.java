package com.themelove.androidlearn.demo.dagger2;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
public class UserManager {
    public ApiService mApiService;
    public UserStore mUserStore;
    public UserManager(ApiService apiService, UserStore userStore){
        this.mApiService=apiService;
        this.mUserStore=userStore;
    }

    public void register(String url){
        mApiService.register(url);
    }
}
