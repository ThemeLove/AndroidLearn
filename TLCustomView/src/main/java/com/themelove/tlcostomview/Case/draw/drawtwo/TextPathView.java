package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 沿着指定路径绘制文字
 * Created by qingshanliao on 2017/7/4.
 */
public class TextPathView extends View{
    public TextPathView(Context context) {
        this(context,null);
    }

    public TextPathView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setTextSize(45);
        paint.setStyle(Paint.Style.STROKE);

        Path cwCircle = new Path();
        cwCircle.addCircle(150,150,100, Path.Direction.CW);
        canvas.drawPath(cwCircle,paint);
        paint.setColor(Color.RED);
        canvas.drawTextOnPath("青春如同奔流的江河",cwCircle,0f,15f,paint);
        canvas.drawText("Direction.CW(顺时针)",0,400,paint);

        Path ccwCircle=new Path();
        ccwCircle.addCircle(650,150,100,Path.Direction.CCW);
        paint.setColor(Color.GREEN);
        canvas.drawPath(ccwCircle,paint);
        paint.setColor(Color.RED);
        canvas.drawTextOnPath("青春如同奔流的江河",ccwCircle,0f,15,paint);
        canvas.drawText("Direction.CCW(逆时针)",450,400,paint);
    }
}
