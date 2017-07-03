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
 * 圆角矩形
 * Created by qingshanliao on 2017/7/3.
 */
public class RoundRectView extends View {
    private Context mContext;
    public RoundRectView(Context context) {
        this(context,null);
    }

    public RoundRectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        paint.setTextSize(30);

        canvas.drawColor(Color.WHITE);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawRoundRect(50,50,250,250,10,10,paint);
        }else{
            RectF rectF = new RectF(50,50,250,250);
            canvas.drawRoundRect(rectF,10,10,paint);
        }
        canvas.drawText("圆角矩形（坐标）(radius=10)",50,400,paint);


        paint.setColor(Color.MAGENTA);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawRoundRect(650,50,850,250,30,30,paint);
        }else{

            RectF rectF = new RectF(650,50,850,250);
            canvas.drawRoundRect(rectF,30,30,paint);
        }
        canvas.drawText("圆角矩形（RectF）(radius=30)",650,400,paint);
    }
}
