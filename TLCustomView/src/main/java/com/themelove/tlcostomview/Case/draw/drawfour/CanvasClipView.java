package com.themelove.tlcostomview.Case.draw.drawfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by lqs on 2017/7/5.
 */
public class CanvasClipView extends View {

    public CanvasClipView(Context context) {
        this(context,null);
    }

    public CanvasClipView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);

        canvas.drawColor(Color.CYAN);

        canvas.drawText("蓝色部分为裁剪的画布，该层画布可见区域只有那么大",0,230,paint);
        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.clipRect(rectF);
        canvas.drawColor(Color.BLUE);

    }
}

