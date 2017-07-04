package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 沿着弧形路径写字的View
 * Created by qingshanliao on 2017/7/4.
 */
public class TextPathArcView extends View {
    public TextPathArcView(Context context) {
        this(context,null);
    }

    public TextPathArcView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextPathArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);    //设置线头为圆角
        paint.setStrokeJoin(Paint.Join.ROUND);  //设置线段连接处为圆角
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);                //设置画笔宽度
        paint.setTextSize(30);

        Path path = new Path();
        path.addArc(new RectF(0,0,600,600),-90,150);
        canvas.drawPath(path,paint);
        paint.setColor(Color.BLUE);
        canvas.drawTextOnPath("天上痴情种，不是人间富贵花",path,30,15,paint);
    }
}
