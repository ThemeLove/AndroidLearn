package com.themelove.androidlearn.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.themelove.androidlearn.AppBase.TLApplication;

/**
 * Created by qingshanliao on 2017/2/16.
 */
public class DrawableUtil {
    public static Bitmap getBitmapFromRes(int resId) {
        return BitmapFactory.decodeResource(TLApplication.getApplication().getResources(), resId);
    }
}
