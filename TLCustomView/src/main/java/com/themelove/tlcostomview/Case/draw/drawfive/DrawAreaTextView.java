package com.themelove.tlcostomview.Case.draw.drawfive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 文字绘制区域自定义控件,所绘文字宽高和绘制文字最小矩形
 * Created by qingshanliao on 2017/8/1.
 */
public class DrawAreaTextView extends View {
    public DrawAreaTextView(Context context) {
        this(context,null);
    }

    public DrawAreaTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawAreaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        String drawText="ThemeLove's Blog";

        paint.setStrokeWidth(5);
        paint.setTextSize(100);

        int baseLineY=200;

//      获取绘制文字要求的矩形坐标,宽用MeasureText测量，高用bottom-top获得
        float requestWidth = paint.measureText(drawText);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float requestHeight = fontMetrics.bottom - fontMetrics.top;

        int requestStartX=0;
        int requestStartY=baseLineY+(int)fontMetrics.top;
        int requestEndX=(int)requestWidth;
        int requestEndY=baseLineY+(int)fontMetrics.bottom;

//      绘制矩形
        paint.setColor(Color.RED);
        canvas.drawRect(new Rect(requestStartX,requestStartY,requestEndX,requestEndY),paint);

//      计算绘制文字区域最小矩形坐标,宽高用getTextBounds获得
        paint.setColor(Color.GREEN);
        Rect boundRect=new Rect();
        paint.getTextBounds(drawText,0,drawText.length(),boundRect);

        int minStartX =boundRect.left;
        int minStartY = boundRect.top + baseLineY;
        int minEndX =boundRect.right;
        int minEndY = boundRect.bottom + baseLineY;
        canvas.drawRect(new Rect(minStartX,minStartY,minEndX,minEndY),paint);

//      绘制文字
        paint.setColor(Color.BLUE);
        canvas.drawText(drawText,0,baseLineY,paint);

        paint.setTextSize(30);
        canvas.drawText("以上红色区域为绘制文字要求的矩形，绿色部分为绘制文字要求最小矩形",0,300,paint);
    }
}
