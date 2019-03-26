package com.themelove.androidlearn.utils;

import android.widget.FrameLayout;

/**
 * 布局参数管理类
 * author:qingshanliao
 * date:2018/1/29
 */
public class LayoutParamsUtil {

    public static FrameLayout.LayoutParams createWrapFrameLayoutParams(){
        return new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

}
