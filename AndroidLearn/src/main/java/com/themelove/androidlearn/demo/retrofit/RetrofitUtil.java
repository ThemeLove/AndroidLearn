package com.themelove.androidlearn.demo.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private final String TAG=RetrofitUtil.class.getSimpleName();
    private static Context mContext;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private static int mMaxCacheTime = 60;
    private static boolean mIsUserCache;

    public static void setMaxCacheTime(int maxCacheTime){
        mMaxCacheTime=maxCacheTime;
    }

    public static void setIsUseCache(boolean isUserCache){
        mIsUserCache=isUserCache;
    }

    public static void init(Context context){
        mContext=context;
        initOkHttp();
        initRetrofit();
    }

    private  static void initOkHttp() {
        if(okHttpClient!=null) return;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)//读取超时时间
                .writeTimeout(20,TimeUnit.SECONDS)//写入超时时间
                .retryOnConnectionFailure(true);//错误重连
        okHttpClient=builder.build();
    }

    private static void initRetrofit() {
        if(retrofit!=null) return;
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(ApiConstantUrl.DELIVERY)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
        retrofit = builder.build();
    }

    @Nullable
    public static <T> T createApiServer(Class<T> clazz){
        if (retrofit==null) return null;
        return retrofit.create(clazz);
    }

    public static  <T> void toSubscribe(@NonNull Observable<T> obserable, @NonNull Observer<T> subscriber){
        obserable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
