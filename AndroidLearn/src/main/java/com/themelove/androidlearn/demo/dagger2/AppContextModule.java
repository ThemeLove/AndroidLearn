package com.themelove.androidlearn.demo.dagger2;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
@Module
public class AppContextModule {
    private Context mContext;
    public AppContextModule(Context context){
        this.mContext=context;
    }

    @Provides
    public Context provideContext(){
        return this.mContext;
    }
}
