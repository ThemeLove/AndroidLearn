package com.themelove.tlcostomview.Case.draw.drawsix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 跟随手势移动的View
 * Created by qingshanliao on 2017/8/1.
 */
public class GestureTrackView extends View {

    private float xInView;
    private float yInView;
    private Path gesturePath;

    public GestureTrackView(Context context) {
        this(context,null);
    }

    public GestureTrackView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GestureTrackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gesturePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawColor(Color.CYAN);
        canvas.drawPath(gesturePath,paint);

        canvas.drawText("手势移动的View,写字板效果",0,50,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                gesturePath.moveTo(xInView, yInView);
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                xInView = event.getX();
                yInView = event.getY();
                gesturePath.lineTo(xInView,yInView);
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void resetCanvas(){
        gesturePath.reset();
        postInvalidate();
    }
}
