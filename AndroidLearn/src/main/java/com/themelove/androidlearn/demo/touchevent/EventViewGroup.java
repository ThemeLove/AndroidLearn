package com.themelove.androidlearn.demo.touchevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by qingshanliao on 2017/8/4.
 */

public class EventViewGroup extends LinearLayout {

    public EventViewGroup(Context context) {
        this(context,null);
    }

    public EventViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public EventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("dispatchTouchEvent","eventViewGroup");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        Log.i("onInterceptHoverEvent","eventViewGroup");
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("onTouchEvent","eventViewGroup");
        return super.onTouchEvent(event);
    }

}
