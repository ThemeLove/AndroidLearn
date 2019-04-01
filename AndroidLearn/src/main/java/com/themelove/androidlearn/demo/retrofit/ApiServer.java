package com.themelove.androidlearn.demo.retrofit;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {

    @FormUrlEncoded
    @GET("user/getcurrentuserprofile")
    Observable<RetrofitBean> getUserBean(@Query("name") String name, @Query("password") String password);
}
