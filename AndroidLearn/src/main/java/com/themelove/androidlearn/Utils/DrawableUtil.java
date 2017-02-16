package com.themelove.androidlearn.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.themelove.androidlearn.AppBase.ThemeLoveApplication;
import com.themelove.androidlearn.R;

/**
 * Created by qingshanliao on 2017/2/16.
 */
public class DrawableUtil {
    public static Bitmap getBitmapFromRes(int resId) {
        return BitmapFactory.decodeResource(ThemeLoveApplication.getApplication().getResources(), resId);
    }
}
