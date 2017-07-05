package com.themelove.tlcostomview.Case.draw.drawthree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qingshanliao on 2017/7/5.
 */

public class RegionMergeOneView extends View {
    public RegionMergeOneView(Context context) {
        this(context,null);
    }

    public RegionMergeOneView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RegionMergeOneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        Rect rect1 = new Rect(100, 0, 200, 300);
        Rect rect2 = new Rect(0, 100, 300, 200);
        canvas.drawRect(rect1,paint);
        canvas.drawRect(rect2,paint);

        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);
        region1.op(region2, Region.Op.INTERSECT);

        paint.setStyle(Paint.Style.FILL);
        drawRegion(canvas,paint,region1);
        canvas.drawText("交集Op.INTERSECT",0,400,paint);


        Rect rect3 = new Rect(500, 0, 600, 300);
        Rect rect4 = new Rect(400, 100, 700, 200);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect3,paint);
        canvas.drawRect(rect4,paint);
        canvas.drawRect(rect3,paint);
        canvas.drawRect(rect4,paint);

        Region region3 = new Region(rect3);
        Region region4 = new Region(rect4);

        paint.setStyle(Paint.Style.FILL);
        region3.op(region4, Region.Op.DIFFERENCE);
        drawRegion(canvas,paint,region3);
        canvas.drawText("补集Op.DIFFERENCE",400,400,paint);
    }

    private void drawRegion(Canvas canvas,Paint paint, Region region){
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)){
            canvas.drawRect(rect,paint);
        }

    }
}

