package com.themelove.androidlearn.demo.dagger2.test;

import android.util.Log;

public class AppBean {
    private final String TAG=AppBean.class.getSimpleName();

    private String mAppName="";
    private int mAppType=1;

    public AppBean(String appName){
        this.mAppName=appName;
    }

    public AppBean(int appType){
        this.mAppType=appType;
    }

    public void getAppInfo(){
        Log.i(TAG,"appName="+this.mAppName+",appType="+mAppType+",hashCode="+hashCode());
    }
}
