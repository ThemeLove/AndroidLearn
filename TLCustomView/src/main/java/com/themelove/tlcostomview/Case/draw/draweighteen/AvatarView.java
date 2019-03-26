package com.themelove.tlcostomview.Case.draw.draweighteen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.themelove.tlcostomview.R;

/**
 * 自定义头像控件
 * Created by qingshanliao on 2017/7/28.
 */
public class AvatarView extends View {
    private Context mContext;
    private Bitmap mAvatarBitmap;//头像地址
    private int mType;           //头像样式0：圆形头像 1：圆角矩形头像
    private int mRoundRadius;    //圆角矩形圆角半斤
    private Paint mPaint;
    private BitmapShader mBitmapShader;

    public AvatarView(Context context) {
        this(context,null);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        mContext=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.AvatarView);
        int resourceId = typedArray.getResourceId(R.styleable.AvatarView_src, -1);
        if (resourceId==-1){
            throw new RuntimeException("AvatarView需要图片属性");
        }
        mAvatarBitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        mType = typedArray.getInt(R.styleable.AvatarView_type, 0);
        mRoundRadius = typedArray.getInt(R.styleable.AvatarView_round_radius, 0);
        typedArray.recycle();

        mPaint = new Paint();
        mBitmapShader = new BitmapShader(mAvatarBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int avatarBitmapWidth=0;
        int avatarBitmapHeight=0;
        if (mAvatarBitmap!=null){
            avatarBitmapWidth= mAvatarBitmap.getWidth();
            avatarBitmapHeight=mAvatarBitmap.getHeight();
        }
        //重新测量控件宽高，当xml中不是EXACTLY的模式的时候，就设置控件宽高为当前图片资源的宽高
        setMeasuredDimension((measureWidthMode==MeasureSpec.EXACTLY)?measureWidth:avatarBitmapWidth,(measureHeightMode==MeasureSpec.EXACTLY)?measureHeight:avatarBitmapHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int scaleX = getWidth() / mAvatarBitmap.getWidth();
        int scaleY = getHeight()/mAvatarBitmap.getHeight();
        Log.i("viewWidth",getWidth()+"");
        Log.i("viewheight",getHeight()+"");

        Log.i("mAvatarBitmapWidth",mAvatarBitmap.getWidth()+"");
        Log.i("mAvatarBitmapHeight",mAvatarBitmap.getHeight()+"");

        Log.i("scaleX",scaleX+"");
        Log.i("scaleY",scaleY+"");

        //特别注意，这个getWidth()要先转化成float，不然都要
        float scaleFloatX = (float)getWidth()/mAvatarBitmap.getWidth();
        float scaleFloatY = (float)getHeight()/mAvatarBitmap.getHeight();

        Log.i("scaleFloatX",scaleFloatX+"");
        Log.i("scaleFloatY",scaleFloatY+"");

        Matrix matrix = new Matrix();
        matrix.setScale(scaleFloatX,scaleFloatY);

        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);
        if (mType==0){//绘制圆形头像
            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);

        }else if(mType==1){//绘制圆角矩形头像
            canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),mRoundRadius,mRoundRadius,mPaint);
        }
    }
}
