package com.themelove.androidlearn.base;

import android.app.Application;

import com.themelove.androidlearn.demo.dagger2.test.ActivityModule;
import com.themelove.androidlearn.demo.dagger2.test.AppBean;
import com.themelove.androidlearn.demo.dagger2.test.AppModule;
import com.themelove.androidlearn.demo.dagger2.test.DaggerAppComponet;
import com.themelove.androidlearn.demo.dagger2.test.IntQualifier;
import com.themelove.androidlearn.demo.dagger2.test.StringQualifier;

import javax.inject.Inject;

/**
 * 自定义Application
 * Created by qingshanliao on 2017/2/16.
 */
public class TLApplication extends Application {
    private static Application instance;

    @StringQualifier
    @Inject
    AppBean stringAppBean;

    @IntQualifier
    @Inject
    AppBean intAppBean;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        TLUnCaughExceptionHandler.getInstance().init(getApplicationContext());
        DaggerAppComponet.builder().appModule(new AppModule()).build().inject(this);

        stringAppBean.getAppInfo();
        intAppBean.getAppInfo();
    }

    public static Application getApplication(){
        return instance;
    }
}
