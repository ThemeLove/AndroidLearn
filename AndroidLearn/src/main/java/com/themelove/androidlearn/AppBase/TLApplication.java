package com.themelove.androidlearn.AppBase;

import android.app.Application;

/**
 * Created by qingshanliao on 2017/2/16.
 */

public class TLApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static Application getApplication(){
        return instance;
    }
}
