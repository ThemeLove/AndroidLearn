package com.themelove.androidlearn.demo.retrofit;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.TLActivity;
import com.themelove.androidlearn.core.views.FlowLayout;
import com.themelove.androidlearn.utils.DisplayUtil;
import com.themelove.androidlearn.utils.TipUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RetrofitTestActivity extends TLActivity implements View.OnClickListener {
    private final String TAG = RetrofitTestActivity.class.getSimpleName();
    private FlowLayout mFlowLayout;
    private TextView mContent;
    private String [] flowDataArray = new String[]{"create","map","flapMap","contractMap"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不要状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retrofit);

        initView();
        initData();
    }
    private void initView() {
        mFlowLayout = findViewById(R.id.flow);
        mContent = findViewById(R.id.content);
    }
    private void initData() {
        RetrofitUtil.init(this.getApplicationContext());
        ApiServer apiServer = RetrofitUtil.createApiServer(ApiServer.class);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(DisplayUtil.dp2px(5),DisplayUtil.dp2px(5),DisplayUtil.dp2px(5),DisplayUtil.dp2px(5));
        for (int i=0;i<flowDataArray.length;i++){
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.START);
            textView.setBackgroundResource(R.drawable.selector_text_btn);
            textView.setClickable(true);
            textView.setEnabled(true);
            textView.setFocusable(true);
            textView.setText(flowDataArray[i]);
            textView.setPadding(DisplayUtil.dp2px(10),DisplayUtil.dp2px(10),DisplayUtil.dp2px(10),DisplayUtil.dp2px(10));
            textView.setTextColor(Color.BLACK);
            textView.setOnClickListener(this);
            mFlowLayout.addView(textView,params);
        }
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
                        final Observable<DeliveryBean> observable2=apiServer.getDelivery("zhongtong", "123");
       /*                 showCommitMessageDialog("getDelivery", deliveryBean.message, "下一步", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                observable2=
                            }
                        });*/
                        return observable2;
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

    @Override
    public void onClick(View v) {
        if (v instanceof TextView){
            String content = (String) ((TextView) v).getText();
            switch (content){
                case "create":
                    TipUtil.showToast("create");
                    create();
                    break;
                case "map":
                    TipUtil.showToast("map");
                    break;
                case "flapMap":
                    TipUtil.showToast("flapMap");
                    break;
                case "contractMap":
                    TipUtil.showToast("contractMap");
                    break;
                default:
                    break;
            }
        }
    }

    private void create(){

        new AlertDialog.Builder(this);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("test");
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .delay(2, TimeUnit.SECONDS)
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"create----->onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"create----->onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"create----->onError");
            }

            @Override
            public void onComplete() {
                TipUtil.showToast("create----->onComplete");
                Log.i(TAG,"create----->onComplete");
            }
        });
    }
}
