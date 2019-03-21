package com.themelove.androidlearn.Case.linkjump2apppage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.themelove.androidlearn.base.TLActivity;
import com.themelove.androidlearn.utils.LayoutParamsUtil;

/**
 * 外部连接跳转到app指定页面[app开发过程中经常有需求是点击外链跳转到指定app的指定页面]
 *
 * 注意：
 * 1、微信内置浏览器屏蔽了外链跳转到app这一功能，所有要在系统浏览器或者浏览器中测试。
 * 2、测试的html页面我放到了assets页面下了，可以通过手机浏览器打开
 * author:qingshanliao
 * date:2018/1/29
 */
public class LinkJump2AppPageActivity extends TLActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout.LayoutParams wrapFrameLayoutParams = LayoutParamsUtil.createWrapFrameLayoutParams();
        wrapFrameLayoutParams.gravity=Gravity.CENTER;
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20,20,20,20);
        textView.setText("我是被外链唤起的");
        setContentView(textView,wrapFrameLayoutParams);
    }

}
