package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 字体
 * Created by qingshanliao on 2017/7/4.
 */
public class TextView extends View {
    public TextView(Context context) {
        this(context,null);
    }

    public TextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);//画笔宽度


//      字体相关设置
        paint.setTextSize(45);
        paint.setTextAlign(Paint.Align.LEFT);

//      正常
        canvas.drawText("山不在高，有仙则名；水不在深，有龙则灵！（正常）",0,50,paint);
        paint.setFakeBoldText(true);//加粗

//      加粗
        canvas.drawText("山不在高，有仙则名；水不在深，有龙则灵！（加粗）",0,100,paint);

//      下划线
        paint.setUnderlineText(true);
        paint.setFakeBoldText(false);
        canvas.drawText("山不在高，有仙则名；水不在深，有龙则灵！（下划线）",0,150,paint);

//      删除线
        paint.setUnderlineText(false);
        paint.setStrikeThruText(true);
        canvas.drawText("山不在高，有仙则名；水不在深，有龙则灵！（删除线）",0,200,paint);

//      倾斜 （向左）
        paint.setStrikeThruText(false);
        paint.setTextSkewX(0.5f);
        canvas.drawText("山不在高，有仙则名；（倾斜（0.5向左））",0,250,paint);

//      倾斜 （向右）
        paint.setTextSkewX(-0.5f);
        canvas.drawText("山不在高，有仙则名；（倾斜（-0.5向右））",0,300,paint);

//      缩放 （放大1倍）
        paint.setTextSkewX(0);
        paint.setTextScaleX(2f);
        canvas.drawText("山不（x轴缩放（放大1倍））",0,350,paint);

//      缩放 （缩小1倍）
        paint.setTextSkewX(0);
        paint.setTextScaleX(0.5f);
        canvas.drawText("山不在高，有仙则名；（x轴缩放（缩小1倍））",0,400,paint);
    }
}
