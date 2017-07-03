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

public class OvalView extends View {
    private Context mContext;

    public OvalView(Context context) {
        this(context,null);
    }

    public OvalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OvalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        paint.setStrokeWidth(5);

        canvas.drawColor(Color.GRAY);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawOval(50,50,250,200,paint);
        }else{
            canvas.drawOval(new RectF(50,50,250,200),paint);
        }

        canvas.drawText("椭圆STROKE",50,400,paint);

        paint.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            canvas.drawOval(650,50,850,200,paint);
        }else{
            canvas.drawOval(new RectF(650,50,850,200),paint);
        }
        canvas.drawText("椭圆FILL",650,400,paint);
    }
}
