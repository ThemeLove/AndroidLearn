package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 指定文字位置的自定义View
 * Created by qingshanliao on 2017/7/4.
 */

public class TextPositionView extends View {
    public TextPositionView(Context context) {
        super(context);
    }

    public TextPositionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPositionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);
        paint.setTextSize(80);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPosText("指定位置",new float[]{0,60,80,140,160,220,240,300},paint);
    }
}
