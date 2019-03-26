package com.themelove.androidlearn.demo.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by qingshanliao on 2017/8/4.
 */
public class EventView extends TextView {
    public EventView(Context context) {
        this(context,null);
    }

    public EventView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("onTouchEvent","eventView");
//        return super.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("dispatchTouchEvent","eventView");
        return super.dispatchTouchEvent(event);
//        return false;
    }
}
