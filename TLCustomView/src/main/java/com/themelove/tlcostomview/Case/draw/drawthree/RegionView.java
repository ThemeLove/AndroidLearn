package com.themelove.tlcostomview.Case.draw.drawthree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

/**
 * 区域的自定义View
 * Created by qingshanliao on 2017/7/5.
 */
public class RegionView extends View {
    public RegionView(Context context) {
        this(context,null);
    }

    public RegionView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RegionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Region region = new Region();
        region.set(new Rect(0,0,200,200));
        drawRegion(canvas,paint,region);
        canvas.drawText("矩形区域",0,400,paint);

        Path path = new Path();
        path.addOval(new RectF(400,0,600,400), Path.Direction.CW);
        Region region1=new Region();
        region1.set(new Rect(400,0,600,200));

        paint.setColor(Color.RED);
        canvas.drawPath(path,paint);
        canvas.drawRect(new Rect(400,0,600,200),paint);


        region1.setPath(path,region1);


        paint.setColor(Color.BLUE);
        drawRegion(canvas,paint,region1);
        canvas.drawText("椭圆区域和矩形区域的交集",400,400,paint);
        paint.setColor(Color.RED);
        canvas.drawRect(new Rect(400,0,600,200),paint);

    }

    private void drawRegion(Canvas canvas,Paint paint,Region region){
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)){
            canvas.drawRect(rect,paint);
        }
    }

}
