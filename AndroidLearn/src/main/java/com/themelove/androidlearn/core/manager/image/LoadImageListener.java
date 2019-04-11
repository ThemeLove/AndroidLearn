package com.themelove.androidlearn.core.manager.image;

import android.graphics.Bitmap;

/**
 * author:qingshanliao
 * date:2019/4/11
 */
public interface LoadImageListener {
     void success(Bitmap bitmap);
     void fail(String msg);
}
