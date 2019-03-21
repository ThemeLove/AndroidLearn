package com.themelove.androidlearn.base;

import android.app.Application;

import com.themelove.androidlearn.utils.DisplayUtil;
import com.themelove.androidlearn.utils.TipUtil;

/**
 * 自定义Application
 * Created by qingshanliao on 2017/2/16.
 */
public class TLApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        TLUnCaughExceptionHandler.getInstance().init(getApplicationContext());
    }

    public static Application getApplication(){
        return instance;
    }
}
