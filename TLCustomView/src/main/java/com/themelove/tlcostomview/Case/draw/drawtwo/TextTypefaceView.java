package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 指定字体的自定义字体
 * Created by qingshanliao on 2017/7/4.
 */
public class TextTypefaceView extends View {
    private Context mContext;
    public TextTypefaceView(Context context) {
        this(context,null);
    }

    public TextTypefaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextTypefaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(65);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);


        canvas.drawColor(Color.GRAY);
//      默认
        canvas.drawText("有位佳人，在水一方!(默认)",0,150,paint);
//      华康娃娃体
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/huakangwawa.TTF");
        paint.setTypeface(typeface);
        canvas.drawText("有位佳人，在水一方!(华康娃娃体)",0,250,paint);
    }
}
