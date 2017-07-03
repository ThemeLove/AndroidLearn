package com.themelove.androidlearn.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.themelove.androidlearn.base.TLApplication;

/**
 * Created by qingshanliao on 2017/2/16.
 */

public class BitmapUtil {
    public static Bitmap getBitmapFromRes(int resId) {
        return BitmapFactory.decodeResource(TLApplication.getApplication().getResources(), resId);
    }
}
