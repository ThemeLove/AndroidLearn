package com.themelove.androidlearn.demo.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    public static <T> Observable<T> getObserable(){
       return Observable.create(new ObservableOnSubscribe<T>(){
            @Override
            public void subscribe(ObservableEmitter<T> observableEmitter) {

            }
        });
    }

    public static <T> T createApiService(Class<T> serviceClass){
        return new Retrofit.Builder().baseUrl(ApiConstantUrl.BASE_RUL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(serviceClass);
    }



}
