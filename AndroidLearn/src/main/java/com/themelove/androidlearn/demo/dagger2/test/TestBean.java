package com.themelove.androidlearn.demo.dagger2.test;

import android.util.Log;

import javax.inject.Inject;

public class TestBean {
    private final String TAG = TestBean.class.getSimpleName();
    private String name ="";
/*    @Inject
    public TestBean(){
        Log.i(TAG,"TestBean create by empty constructor ");
    }*/

    @Inject
    public TestBean(String name){
        this.name= name;
        Log.i(TAG,"TestBean create by String param constructor with name="+name);
    }

    public void getTestBeanInfo(){
        Log.i(TAG,"TestBean's name="+name+" ,hasCode="+hashCode());
    }
}
