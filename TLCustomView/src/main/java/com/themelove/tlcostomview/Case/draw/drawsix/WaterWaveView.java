package com.themelove.tlcostomview.Case.draw.drawsix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 水波纹效果的自定义View 利用三阶贝塞尔曲线实现
 * Created by qingshanliao on 2017/8/1.
 */
public class WaterWaveView extends View {
    public WaterWaveView(Context context) {
        this(context,null);
    }

    public WaterWaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaterWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);

        Path path = new Path();



    }


}
