package com.themelove.androidlearn.demo.dagger2.test;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private String name;

    public ActivityModule(String moduleName){
        this.name=moduleName;
    }

    @StringQualifier
    @Provides
    @ActivityScope
    public ActivityBean provideActivityBeanWithString(){

        return new ActivityBean("mainActivity");
    }
    @IntQualifier
    @Provides
    @ActivityScope
    public ActivityBean provideActivityBeanWithInt(){
        return new ActivityBean(2);
    }

    @Provides
    @ActivityScope
    public String provideTestBeanStringParam(){
        return "testBean";
    }
}
