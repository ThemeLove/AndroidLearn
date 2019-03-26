package com.themelove.androidlearn.Case.touchevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.themelove.androidlearn.R;

/**
 * 测试事件传递机制的Activity
 * Created by qingshanliao on 2017/8/4.
 */
public class TouchEventActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent);

        initView();
    }

    private void initView() {
        View eventView = findViewById(R.id.event_view);
        Button eventButton = (Button) findViewById(R.id.event_button);
        EventView eventTextView = (EventView) findViewById(R.id.event_textView);

        final GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        };

        eventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TouchEventActivity.this,"onClik",Toast.LENGTH_SHORT).show();
                Log.i("onClick","eventView");
            }
        });

        eventTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(TouchEventActivity.this, onGestureListener);
                gestureDetectorCompat.onTouchEvent(event);
                Log.i("onTouch","eventView");
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("dispatchTouchEvent","activity");

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("onTouchEvent","activity");
        return super.onTouchEvent(event);
    }
}
