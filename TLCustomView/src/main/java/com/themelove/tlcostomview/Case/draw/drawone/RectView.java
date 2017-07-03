package com.themelove.tlcostomview.Case.draw.drawone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画矩形的View
 * Created by qingshanliao on 2017/7/3.
 */
public class RectView extends View {
    private Context mContext;
    public RectView(Context context) {
        this(context,null);
    }

    public RectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        canvas.drawColor(Color.GRAY);

        canvas.drawRect(50,50,200,200,paint);
        canvas.drawText("Style.FILL画一个矩形",0,400,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        Rect rect = new Rect(450, 50, 600, 200);
        canvas.drawRect(rect,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Style.STROKE画一个矩形（Rect）",350,400,paint);

        paint.setColor(Color.BLUE);
        RectF rectF = new RectF(850,50,1000,200);
        canvas.drawRect(rectF,paint);
        canvas.drawText("Style.FILL画一个矩形（RectF）",650,300,paint);
    }
}
