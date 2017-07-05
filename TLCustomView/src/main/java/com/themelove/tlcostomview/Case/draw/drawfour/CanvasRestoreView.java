package com.themelove.tlcostomview.Case.draw.drawfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lqs on 2017/7/6.
 */

public class CanvasRestoreView extends View {
    public CanvasRestoreView(Context context) {
        this(context,null);
    }

    public CanvasRestoreView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasRestoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        canvas.save();

        RectF rectF = new RectF(100, 100, 800, 800);
        canvas.clipRect(rectF);
        canvas.drawColor(Color.YELLOW);
        canvas.save();

        RectF rectF1 = new RectF(200, 200, 700, 700);
        canvas.clipRect(rectF1);
        canvas.drawColor(Color.GREEN);
        canvas.save();

        RectF rectF2 = new RectF(300, 300, 600, 600);
        canvas.clipRect(rectF2);
        canvas.drawColor(Color.BLUE);
        canvas.save();

        RectF rectF3 = new RectF(400, 400, 500, 500);
        canvas.clipRect(rectF3);
        canvas.drawColor(Color.WHITE);

        canvas.restore();
        canvas.restore();
        canvas.restore();

        canvas.drawColor(Color.DKGRAY);


        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        canvas.drawText("restroe",450,450,paint);
    }
}
