package com.themelove.tlcostomview.Case.draw.drawone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画直线的自定义View
 * Created by qingshanliao on 2017/7/3.
 */
public class LineView extends View {
    private Context mContext;

    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);

        canvas.drawColor(Color.WHITE);
        canvas.drawLine(20,20,150,150,paint);

        canvas.drawText("画一条线",50,400,paint);
//      画多条线
        canvas.drawLines(new float[]{320,20,400,100,420,120,500,200},paint);
        canvas.drawText("画多条线",350,400,paint);

//      画多条线
        canvas.drawLines(new float[]{620,20,700,100,720,120,800,200,820,220,900,300},4,8,paint);
        canvas.drawText("画多条线",650,400,paint);
    }
}
