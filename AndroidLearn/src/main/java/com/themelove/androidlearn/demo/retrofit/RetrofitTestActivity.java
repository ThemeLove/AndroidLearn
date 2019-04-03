package com.themelove.androidlearn.demo.retrofit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RetrofitTestActivity extends AppCompatActivity {
    private final String TAG = RetrofitTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("开始请求");
        textView.setWidth(300);
        textView.setHeight(50);
        textView.setBackgroundColor(Color.CYAN);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);

        textView.setOnClickListener(v -> getDelivery());  //lambda的写法
    }

    private void getDelivery() {
        RetrofitUtil.init(this.getApplicationContext());
        ApiServer apiServer = RetrofitUtil.createApiServer(ApiServer.class);
//        Observable<DeliveryBean> deliveryBeanObservable = apiServer.getDelivery("zhongtong", "75136378475307");
        Observable<DeliveryBean> deliveryBeanObservable = apiServer.getDelivery("zhongtong", "75136378475307")
                .doOnNext(new Consumer<DeliveryBean>() {
                    @Override
                    public void accept(DeliveryBean deliveryBean) throws Exception {
                        Log.i(TAG,"accept----->deliveryBean.message="+deliveryBean.message);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<DeliveryBean, ObservableSource<DeliveryBean>>() {
                    @Override
                    public ObservableSource<DeliveryBean> apply(DeliveryBean deliveryBean) throws Exception {
                        Log.i(TAG,"flatMap----->deliveryBean.message="+deliveryBean.message);
                       return apiServer.getDelivery("zhongtong", "123");
                    }
                });
        RetrofitUtil.toSubscribe(deliveryBeanObservable, new Observer<DeliveryBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(DeliveryBean deliveryBean) {
                Log.i(TAG, "onNext");
                if (deliveryBean!=null){
                    Log.i(TAG,"message="+deliveryBean.message);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }
}
