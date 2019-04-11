package com.themelove.androidlearn.core.manager.image;

import android.graphics.Bitmap;

/**
 * author:qingshanliao
 * date:2019/4/11
 */
public interface ImageCache {
    Bitmap getBitmap(String url);
    boolean saveBitmap(String url,Bitmap bitmap);
}
