package com.themelove.tlcostomview.Case.draw.drawfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 旋转画布的自定义View
 * Canvas的旋转默认是（0,0）点为圆心的
 * Created by qingshanliao on 2017/7/5.
 */
public class CanvasRotateView extends View {
    public CanvasRotateView(Context context) {
        this(context,null);
    }

    public CanvasRotateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasRotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);

        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawText("红色是旋转前，蓝色是旋转后，角度30",0,400,paint);
        canvas.drawRect(rectF,paint);
        canvas.rotate(30);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF,paint);
    }
}
