package com.themelove.tlcostomview.Case.draw.drawfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 平移画布的自定义View
 * Created by qingshanliao on 2017/7/5.
 */
public class CanvasTranslateView extends View {
    public CanvasTranslateView(Context context) {
        this(context,null);
    }

    public CanvasTranslateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasTranslateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        RectF rectF = new RectF(0, 0, 100, 100);
        canvas.drawRect(rectF,paint);
        canvas.drawText("平移前",0,400,paint);
        canvas.drawText("canvas水平向右平移600---->",200,200,paint);
        canvas.drawText("相当于canvas的圆点坐标向右平移",200,250,paint);
        canvas.drawText("平移前后画矩形RectF(0,0,100,100)不变",200,300,paint);

        canvas.translate(600,0);
        canvas.drawRect(rectF,paint);
        canvas.drawText("平移后",0,400,paint);
    }
}
