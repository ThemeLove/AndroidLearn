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

public class CircleView extends View {
    private Context mContext;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
//      设置填充样式 Style.FILL:填充 ；Style.FILL_AND_STROKE:填充并且描边；Style.STROKE：描边
        paint.setStyle(Paint.Style.STROKE);
//      设置画笔颜色
        paint.setColor(Color.CYAN);
//      设置抗锯齿
        paint.setAntiAlias(true);
//      设置画笔宽度
        paint.setStrokeWidth(2);
//      设置阴影
        paint.setShadowLayer(10,15,15,Color.BLACK);
//      设置画布颜色
        canvas.drawRGB(255,255,255);


//      Style.STROKE
//      画一个圆：圆心（150,150） radus=100
        canvas.drawCircle(150,150,100,paint);
//      设置字体大小
        paint.setTextSize(40);
        canvas.drawText("圆Style.STROKE",10,300,paint);

//      Style.FILL
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(500,150,100,paint);
        canvas.drawText("圆Style.FILL",360,300,paint);

//      Style.FILL_AND_STROKE
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(850,150,100,paint);
        canvas.drawText("圆Style.FILL_AND_STROKE",650,300,paint);

    }
}
