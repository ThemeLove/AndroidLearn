package com.themelove.androidlearn.demo.dagger2.test;

import com.themelove.androidlearn.base.TLApplication;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponet {
    ActivityComponet provideActivityComponet(ActivityModule activityModule);

    void inject(TLApplication tlApplication);
}
