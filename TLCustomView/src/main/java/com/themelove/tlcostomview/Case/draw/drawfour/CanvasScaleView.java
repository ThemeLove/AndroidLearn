package com.themelove.tlcostomview.Case.draw.drawfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画布缩放的自定义View
 * Created by qingshanliao on 2017/7/5.
 */
public class CanvasScaleView extends View {
    public CanvasScaleView(Context context) {
        this(context,null);
    }

    public CanvasScaleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6);

        RectF rectF = new RectF(0, 0, 400, 200);
        canvas.drawRect(rectF,paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        canvas.drawText("缩放前的正常字体",0,300,paint);
        canvas.scale(0.5f,1);
        canvas.drawRect(rectF,paint);

        canvas.drawText("蓝色为缩放前，红色为x轴缩放0.5倍后的效果,你看连字都变形了",0,400,paint);
    }
}
