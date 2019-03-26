package com.themelove.androidlearn.demo.dagger2.test;

import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules={ActivityModule.class})
public interface ActivityComponet {
     void inject(TestDagger2Activity testDagger2Activity);
}
