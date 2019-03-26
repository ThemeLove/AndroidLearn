package com.themelove.androidlearn.demo.dagger2;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
public class UserStore {
    private String mName;

    @Inject
    public UserStore(String name){
        this.mName=name;
        Log.i("UserStore","name=====>"+name);
    }

}
