package com.themelove.androidlearn.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 屏幕视频工具类
 * author:qingshanliao
 * date:2018/1/26
 */
public class DisplayUtil {
    public static final int DISPLAY_SMALL = 0;
    public static final int DISPLAY_MIDDLE = 1;
    public static final int DISPLAY_LARGE = 2;
    public static final int DISPLAY_XLARGE = 3;

    private static boolean isInitialize = false;
    /**
     * 屏幕宽度
     **/
    public static int screenWidth;
    /**
     * 屏幕高度
     **/
    public static int screenHeight;
    /**
     * 状态栏高度
     */
    public static int statusBarHeight;
    /**
     * 屏幕密度
     **/
    public static int screenDpi;
    /**
     * 逻辑密度, 屏幕缩放因子
     */
    public static float density = 1;
    /**
     * 字体缩放因子
     */
    public static float scaledDensity;
    /**
     * 屏幕类型
     */
    public static int displayType;

    /**
     * 初始化屏幕宽度和高度
     */
    public static void initScreen(Activity activity) {
        if (isInitialize) return;
        isInitialize = true;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metric = new DisplayMetrics();

        // 屏幕宽度、高度、密度、缩放因子
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(metric);
        } else {
            try {
                Class<?> clazz = Class.forName("android.view.Display");
                Method method = clazz.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, metric);
            } catch (Throwable e) {
                display.getMetrics(metric);
            }
        }

        try {
            // 状态栏高度
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Field field = clazz.getField("status_bar_height");
            int x = Integer.parseInt(field.get(clazz.newInstance()).toString());
            statusBarHeight = activity.getResources().getDimensionPixelSize(x);
        } catch (Throwable e) {
            e.printStackTrace();
            Rect outRect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
            statusBarHeight = outRect.top;
        }

        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;
        screenDpi = metric.densityDpi;
        density = metric.density;
        scaledDensity = metric.scaledDensity;

        if (screenWidth >= 320 && screenWidth < 480) {
            displayType = DISPLAY_SMALL;
        } else if (screenWidth >= 480 && screenWidth < 720) {
            displayType = DISPLAY_MIDDLE;
        } else if (screenWidth >= 720 && screenWidth < 1080) {
            displayType = DISPLAY_LARGE;
        } else {
            displayType = DISPLAY_XLARGE;
        }
    }

    /**
     * 是否是横屏
     */
    public static boolean isHorizontal() {
        return screenWidth > screenHeight;
    }

    public static int px2dp(float inParam) {
        return (int) (inParam / density + 0.5F);
    }

    public static int dp2px(float inParam) {
        return (int) (inParam * density + 0.5F);
    }

    public static int px2sp(float inParam) {
        return (int) (inParam / density + 0.5F);
    }

    public static int sp2px(float inParam) {
        return (int) (inParam * density + 0.5F);
    }

    public static int getMeSureHeight(int width, int height) {
        int scaleHeight;
        float scale = (float) screenWidth / (float) width;
        scaleHeight = (int) (scale * height);
        return scaleHeight;
    }
    public static int getMeSureHeight(int baseWidth, int width, int height) {
        int scaleHeight;
        float scale = (float) baseWidth / (float) width;
        scaleHeight = (int) (scale * height);
        return scaleHeight;
    }
}
