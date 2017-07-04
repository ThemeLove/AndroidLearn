package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import static com.themelove.tlcostomview.Case.draw.drawtwo.CircleProcessBar.ProcessState.START;

/**
 * 圆形进度条
 * Created by qingshanliao on 2017/7/4.
 */
public class CircleProcessBar extends View {
    private Context mContext;
    public CircleProcessBar(Context context) {
        this(context,null);
    }

    public CircleProcessBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProcessBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint mReachPaint = new Paint();
        mReachPaint.setStyle(Paint.Style.STROKE);
        mReachPaint.setAntiAlias(true);
        mReachPaint.setColor(mReachColor);
        mReachPaint.setStrokeWidth(mReachPaintWidth);
        mReachPaint.setStrokeCap(Paint.Cap.ROUND);//设置线头为圆角

        Paint mProcessBackPaint=new Paint();
        mProcessBackPaint.setStyle(Paint.Style.STROKE);
        mProcessBackPaint.setAntiAlias(true);
        mProcessBackPaint.setStrokeWidth(5);
        mProcessBackPaint.setStrokeCap(Paint.Cap.ROUND);//设置线头为圆角
        mProcessBackPaint.setColor(0x00000000);

        Paint mTextPaint=new Paint();
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mProcessTextColor);
        mTextPaint.setTextSize(mProcessTextSize);
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);

        Paint mUnReachPaint=new Paint();
        mUnReachPaint.setStyle(Paint.Style.STROKE);
        mUnReachPaint.setAntiAlias(true);
        mUnReachPaint.setColor(mUnReachColor);
        mUnReachPaint.setStrokeWidth(5);
        mUnReachPaint.setStrokeCap(Paint.Cap.ROUND);




        Path mReachPath = new Path();
        float mReachSweepAngle=360*mCurrentProcess/100;
        if (360-mReachSweepAngle<30){
             mReachPath.addArc(new RectF(0+mPadding,0+mPadding,2*mRadius+mPadding,2*mRadius+mPadding),90+(mReachSweepAngle-330),mReachSweepAngle);
        }
        else {
            mReachPath.addArc(new RectF(0+mPadding,0+mPadding,2*mRadius+mPadding,2*mRadius+mPadding),90,mReachSweepAngle);
        }
        canvas.drawPath(mReachPath,mReachPaint);

        Path mProcessBackPath=new Path();
        float mProcessBackAngle=30;
        mProcessBackPath.addArc(new RectF(0+mPadding,0+mPadding,2*mRadius+mPadding,2*mRadius+mPadding),90+mReachSweepAngle,mProcessBackAngle);
        canvas.drawPath(mProcessBackPath,mProcessBackPaint);
        canvas.drawTextOnPath(mCurrentProcess+"%",mProcessBackPath,30,18f,mTextPaint);


        Path mUnReachPath = new Path();
        float mUnReachSweepAngle=360-mReachSweepAngle-mProcessBackAngle;
        if (mUnReachSweepAngle>0){
            mUnReachPath.addArc(new RectF(0+mPadding,0+mPadding,2*mRadius+mPadding,2*mRadius+mPadding),90+mReachSweepAngle+mProcessBackAngle,mUnReachSweepAngle);
            canvas.drawPath(mUnReachPath,mUnReachPaint);
        }


        mTextPaint.setColor(mStateTextColor);
        mTextPaint.setTextSize(60);
        String stateText="";
        float  textLength=0;
        switch (mCurrentState){
            case START:
                stateText="开始";
                textLength= mTextPaint.measureText(stateText);
                canvas.drawText(stateText,mRadius+mPadding-(textLength/2),mRadius+mPadding,mTextPaint);
                break;
            case RUNNINT:
                stateText="暂停";
                canvas.drawText(stateText,mRadius,mRadius,mTextPaint);
                break;
            case POST:
                stateText="继续";
                canvas.drawText(stateText,mRadius,mRadius,mTextPaint);
                break;
            case FINISH:
                stateText="完成";
                canvas.drawText(stateText,mRadius,mRadius,mTextPaint);
                break;
            default:
                break;
        }

        mTextPaint.setStrokeWidth(20);
        canvas.drawPoint(mRadius+mPadding,mRadius+mPadding,mTextPaint);
    }
    private   int                       mPadding                =   30;                 //内部padding
    private   int                       mRadius                 =   250;                //圆的半径
    private   int                       mReachColor             =   Color.GREEN;        //已完成进度的颜色
    private   int                       mUnReachColor           =   Color.YELLOW;       //未完成进度的颜色
    private   float                     mReachPaintWidth        =   5;                 //已完成进度画笔的宽度
    private   float                     mUnReachColorWidth      =   5;                  //未完成进度的颜色
    private   int                       mProcessTextColor       =   Color.BLUE;        //当前进度的字体颜色
    private   float                     mProcessTextSize        =   40;                 //当前进度字体的大小
    private   int                       mStateTextColor         =   Color.BLUE;         //当前状态字体的颜色
    private   float                     mStateTextSize          =   50;                 //当前状态字体的大小
    private   int                       mCurrentProcess         =   50;                  //当前的进度
    private   ProcessState              mCurrentState           = START;    //当前的状态
    private   OnProcessClickListener    mOnProcessClickListener;                        //点击事件监听

    /**
     * 当前进度条状态的枚举
     */
    public enum ProcessState{
        START,          //开始状态
        RUNNINT,        //正在运行状态
        POST,           //暂停状态
        FINISH          //完成状态
    }

    public void setProcess(int process){
        mCurrentProcess=process;
        invalidate();
    }

    public void setOnPorcessClickListener(OnProcessClickListener onPorcessClickListener){
        mOnProcessClickListener=onPorcessClickListener;
    }

    public interface OnProcessClickListener{
        void onProcessClick(ProcessState state,int process);
    }
}
