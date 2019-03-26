package com.themelove.tlcostomview.Case.draw.drawsix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 二阶贝塞尔曲线
 * Created by qingshanliao on 2017/8/1.
 */
public class SecondBezierCurveView extends View {
    public SecondBezierCurveView(Context context) {
        this(context,null);
    }

    public SecondBezierCurveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SecondBezierCurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100,200);
        path.quadTo(250,50,400,200);
        path.quadTo(550,350,700,200);
        canvas.drawPath(path,paint);

        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        canvas.drawText("二阶贝塞尔曲线，path quadTo方法的运用",0,360,paint);
        canvas.drawText("先调用moveTo方法标示贝塞尔曲线的起点",0,400,paint);
    }
}
