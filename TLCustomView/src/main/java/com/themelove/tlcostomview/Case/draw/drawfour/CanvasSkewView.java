package com.themelove.tlcostomview.Case.draw.drawfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * canvas.skew(float sx,float sy) sx为x轴方向倾斜角度对应的tan值，sy为y轴方向倾斜角对应的tan值
 * Created by lqs on 2017/7/5.
 */
public class CanvasSkewView extends View{
    public CanvasSkewView(Context context) {
        this(context,null);
    }

    public CanvasSkewView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasSkewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(100, 0, 400, 200);
        canvas.drawRect(rectF,paint);

//      sx为x轴方向倾斜角度对应的tan值，sy为y轴方向倾斜角对应的tan值
        canvas.skew((float)Math.sqrt(3),0);

        paint.setStrokeWidth(6);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF,paint);

        canvas.drawText("红色是未倾斜，蓝色是倾斜",0,400,paint);
    }
}
