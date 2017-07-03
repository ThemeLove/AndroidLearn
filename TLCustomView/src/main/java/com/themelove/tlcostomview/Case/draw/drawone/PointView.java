package com.themelove.tlcostomview.Case.draw.drawone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qingshanliao on 2017/7/3.
 */

public class PointView extends View{
    private Context mContext;
    public PointView(Context context) {
        this(context,null);
    }

    public PointView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setTextSize(30);

        canvas.drawColor(Color.GRAY);

//      画一个点（50,50）
        canvas.drawPoint(100,100,paint);
        canvas.drawText("画一个点",50,300,paint);

//      画多个点
        canvas.drawPoints(new float[]{360,60,380,80,400,100,420,120,440,140},2,8,paint);
        canvas.drawText("画多个点",360,300,paint);
//      画多个点
        canvas.drawPoints(new float[]{660,60,680,80,700,100,720,120,740,140},paint);
        canvas.drawText("画多个点",660,300,paint);
    }
}
