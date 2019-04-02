package com.themelove.androidlearn.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

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
public class TLApplication extends MultiDexApplication {
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


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Application getApplication(){
        return instance;
    }
}
