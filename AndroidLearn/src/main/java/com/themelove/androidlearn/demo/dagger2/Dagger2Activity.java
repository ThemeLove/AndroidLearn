package com.themelove.androidlearn.demo.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import javax.inject.Inject;

/**
 * author:qingshanliao
 * date:2019/3/25
 */
public class Dagger2Activity extends AppCompatActivity {

    @Inject
    ApiService mApiService;
    @Inject
    UserManager mUserManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        TextView textView = new TextView(this);
        textView.setText("Dagger2的使用");
        setContentView(textView);

        DaggerUserComponet.builder()
                .userModule(new UserModule(this))
                .oKHttpModule(new OKHttpModule())
                .build().inject(this);
        mUserManager.register("http://www.baidu.com");
    }


}
