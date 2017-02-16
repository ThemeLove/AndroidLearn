package com.themelove.androidlearn.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.themelove.androidlearn.AppBase.ThemeLoveApplication;

/**
 * Created by qingshanliao on 2017/2/16.
 */

public class BitmapUtil {
    public static Bitmap getBitmapFromRes(int resId) {
        return BitmapFactory.decodeResource(ThemeLoveApplication.getApplication().getResources(), resId);
    }
}
