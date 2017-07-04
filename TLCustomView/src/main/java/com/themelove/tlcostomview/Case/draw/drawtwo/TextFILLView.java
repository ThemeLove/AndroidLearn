package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 字体填充样式的自定义View (Paint)
 * Created by qingshanliao on 2017/7/4.
 */
public class TextFILLView extends View {

    public TextFILLView(Context context) {
        super(context);
    }

    public TextFILLView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextFILLView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);

        canvas.drawColor(Color.GRAY);
//      填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("（Style.FILL 填充）",0,150,paint);
//      描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("（Style.STROKE 描边）",0,250,paint);
//      填充且描边
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("（填充且描边）",0,400,paint);

    }


}
