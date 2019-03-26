package com.themelove.androidlearn.demo.dagger2;

import dagger.Component;

/**
 * author:qingshanliao
 * date:2019/3/25
 */

@Component(modules={UserModule.class,OKHttpModule.class})
public interface UserComponet {
    void inject(Dagger2Activity activity);
}
