package com.themelove.tlcostomview.Case.draw.draweighteen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.themelove.tlcostomview.R;

/**
 * 望远镜View
 * Created by qingshanliao on 2017/7/28.
 */
public class TelescopeView extends View {
    private Context mContext;
    private Paint mPaint;
    private float mCurrentXInView;
    private float mCurrentYInView;
    private float mTelescopeViewRadius;
    private Bitmap mTelescopeBitmap;
    private boolean misCanDraw;
    private int mScreenWidth;

    public TelescopeView(Context context) {
        this(context, null);
    }

    public TelescopeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelescopeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mScreenWidth,mTelescopeBitmap.getHeight());
    }

    private void init() {
        mTelescopeBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_telescope);
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(50);
        mPaint.setShader(new BitmapShader(mTelescopeBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mTelescopeViewRadius = getWidth() / 8;
        canvas.drawColor(Color.GRAY);
        canvas.drawText("望远镜效果View,手指触摸屏幕测试",80,100,mPaint);
        if (misCanDraw){
            canvas.drawCircle(mCurrentXInView,mCurrentYInView,mTelescopeViewRadius,mPaint);
            canvas.drawCircle(200+mCurrentXInView,mCurrentYInView,mTelescopeViewRadius,mPaint);
        }
    }

    /**
     * 重写OnTouchEvent
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                misCanDraw =true;
                mCurrentXInView = event.getX();
                mCurrentYInView = event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                misCanDraw =true;
                mCurrentXInView=event.getX();
                mCurrentYInView=event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                misCanDraw =false;
                postInvalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }
}
