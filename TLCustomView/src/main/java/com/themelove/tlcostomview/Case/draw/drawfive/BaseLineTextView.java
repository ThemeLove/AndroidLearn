package com.themelove.tlcostomview.Case.draw.drawfive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试文字基线位置的自定义控件
 * Created by qingshanliao on 2017/7/31.
 */
public class BaseLineTextView extends View {

    private Paint mPaint;

    public BaseLineTextView(Context context) {
        this(context,null);
    }

    public BaseLineTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(60);
        mPaint.setColor(Color.CYAN);
        canvas.drawText("下面红点为基线起点，绿线为基线",0,50,mPaint);

        mPaint.setColor(Color.GREEN);
        int baseLineX=3000;
        canvas.drawLine(0,150,baseLineX,150,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawText("ThemeLove's Blog(test BaseLine)",0,150,mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(0,150,mPaint);
    }
}
