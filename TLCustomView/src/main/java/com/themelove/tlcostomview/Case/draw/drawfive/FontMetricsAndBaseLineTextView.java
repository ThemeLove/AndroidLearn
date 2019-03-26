package com.themelove.tlcostomview.Case.draw.drawfive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 展示FontMetrics 中的ascent、descent、top 、bottom四条线和BaseLine
 * Created by qingshanliao on 2017/8/1.
 */
public class FontMetricsAndBaseLineTextView extends View {
    public FontMetricsAndBaseLineTextView(Context context) {
        this(context,null);
    }

    public FontMetricsAndBaseLineTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FontMetricsAndBaseLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(120);
        int baseLineY=250;
        canvas.drawColor(Color.GRAY);

        canvas.drawText("FontMetricsAndBaseLineTextView",0,baseLineY,paint);
//      计算FontMetrics中ascent、descent、top、botton、的y坐标
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int ascentY=baseLineY+(int)fontMetrics.ascent;
        int descentY=baseLineY+(int)fontMetrics.descent;
        int topY=baseLineY+(int)fontMetrics.top;
        int bottomY=baseLineY+(int)fontMetrics.bottom;
//      baseLine
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawLine(0,baseLineY,3000,baseLineY,paint);
        canvas.drawText("base line",500,baseLineY,paint);

//      top line
        canvas.drawLine(0,topY,3000,topY,paint);
        canvas.drawText("top line",500,topY,paint);

//      ascent line
        paint.setColor(Color.GREEN);
        canvas.drawLine(0,ascentY,3000,ascentY,paint);
        canvas.drawText("ascent line",500,ascentY,paint);

//      descent line
        paint.setColor(Color.YELLOW);
        canvas.drawLine(0,descentY,3000,descentY,paint);
        canvas.drawText("descent line",500,descentY,paint);

//      bottom line
        paint.setColor(Color.CYAN);
        canvas.drawLine(0,bottomY,3000,bottomY,paint);
        canvas.drawText("bottom line",500,bottomY,paint);
    }
}
