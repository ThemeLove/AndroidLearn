package com.themelove.tlcostomview.Case.draw.draweighteen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.themelove.tlcostomview.R;

/**
 * Created by qingshanliao on 2017/7/28.
 */
public class TileMode_REPEAT_View extends View {
    private Context mContext;
    private Bitmap mShaderBitmap;
    private Paint mPaint;

    public TileMode_REPEAT_View(Context context) {
        this(context,null);
    }

    public TileMode_REPEAT_View(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TileMode_REPEAT_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }
    private void init(){
        mPaint = new Paint();
        mShaderBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_dog);
        mPaint.setShader(new BitmapShader(mShaderBitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }
}
