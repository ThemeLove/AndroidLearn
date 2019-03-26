package com.themelove.androidlearn.demo.dagger2.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

public class TestDagger2Activity extends AppCompatActivity {
    private final String TAG = TestDagger2Activity.class.getSimpleName();

    @Inject
    @ActivityScope
    TestBean testBean;

    @StringQualifier
    @ActivityScope
    @Inject
    ActivityBean stringActivityBean;

    @IntQualifier
    @ActivityScope
    @Inject
    ActivityBean intActivityBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView =new TextView(this);
        textView.setText(TAG);
        setContentView(textView);
        Log.i(TAG, "onCreate");
        DaggerActivityComponet.builder().activityModule(new ActivityModule("activityModule")).build().inject(this);
//        DaggerAppComponet.builder().appModule(new AppModule()).build().provideActivityComponet(new ActivityModule()).inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        stringActivityBean.getActivityInfo();
        intActivityBean.getActivityInfo();

        testBean.getTestBeanInfo();
    }
}
