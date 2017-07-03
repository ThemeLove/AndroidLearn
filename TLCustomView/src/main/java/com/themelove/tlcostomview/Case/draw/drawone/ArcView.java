package com.themelove.tlcostomview.Case.draw.drawone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qingshanliao on 2017/7/3.
 */

public class ArcView extends View {
    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        paint.setColor(Color.GREEN);
        canvas.drawColor(Color.WHITE);

        canvas.drawArc(new RectF(50,50,250,200),90,60,true,paint);
        canvas.drawText("圆弧FILL(userCenter=true)",50,400,paint);

        paint.setColor(Color.RED);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawArc(200,50,450,200,0,90,false,paint);
        }else{
            canvas.drawArc(new RectF(200,50,450,200),0,90,false,paint);
        }
        canvas.drawText("圆弧FILL(userCenter=false)",200,250,paint);


        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawArc(450,50,650,200,0,90,false,paint);
        }else{
            canvas.drawArc(new RectF(450,50,650,200),0,90,false,paint);
        }
        canvas.drawText("圆弧STROKE(userCenter=false)",450,400,paint);

        paint.setColor(Color.BLUE);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawArc(800,50,1000,200,0,90,true,paint);
        }else{
            canvas.drawArc(new RectF(800,50,1000,200),0,90,true,paint);
        }
        canvas.drawText("圆弧STROKE(userCenter=true)",600,300,paint);
    }
}
