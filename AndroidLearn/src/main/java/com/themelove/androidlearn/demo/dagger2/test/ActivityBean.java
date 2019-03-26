package com.themelove.androidlearn.demo.dagger2.test;

import android.util.Log;

public class ActivityBean {
    private final String TAG = ActivityBean.class.getSimpleName();
    private String mActivityName="";
    private int mActivityType=1;

    public ActivityBean(String activityName){
        this.mActivityName = activityName;
    }

    public ActivityBean(int activityType){
        this.mActivityType=activityType;
    }

    public void getActivityInfo(){
        Log.i(TAG, "activityName="+ mActivityName +",activityType="+mActivityType+",hashCode="+hashCode());
    }
}
