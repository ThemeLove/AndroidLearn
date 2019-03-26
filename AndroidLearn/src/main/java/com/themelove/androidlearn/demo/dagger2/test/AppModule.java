package com.themelove.androidlearn.demo.dagger2.test;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @StringQualifier

    @Provides
    @AppScope
    public AppBean provideAppBeanWithString(){
        return new AppBean("mainApp");
    }

    @IntQualifier
    @Provides
    @AppScope
    public AppBean provideAppBeanWithInt(){
        return new AppBean(2);
    }

}
