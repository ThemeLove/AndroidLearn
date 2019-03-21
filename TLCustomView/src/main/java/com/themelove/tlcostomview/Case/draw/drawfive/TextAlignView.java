package com.themelove.tlcostomview.Case.draw.drawfive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qingshanliao on 2017/7/31.
 */

public class TextAlignView extends View {
    public TextAlignView(Context context) {
        this(context,null);
    }

    public TextAlignView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextAlignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mPaint.setTextSize(120);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("A",0,100,mPaint);

        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("A",0,200,mPaint);

        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("A",0,200,mPaint);

        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Align.LEFT默认值，字符A完全显示",150,100,mPaint);
        canvas.drawText("Align.CENTER，字符A显示一半",150,200,mPaint);
        canvas.drawText("Align.RIGHT，字符A看不见，显示在屏幕左边",150,300,mPaint);
    }
}
