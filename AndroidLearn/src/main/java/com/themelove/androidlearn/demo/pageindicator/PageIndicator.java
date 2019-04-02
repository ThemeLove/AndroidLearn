package com.themelove.androidlearn.demo.pageindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.utils.DisplayUtil;

/**
 * author:qingshanliao
 * date:2019/4/2
 */

public class PageIndicator extends View {

    private Context mContext;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    /**
     * 指示器数量
     */
    private int count;
    /**
     * 用于绘制圆环的画笔
     */
    private Paint paintArc;
    /**
     * 用于绘制是新的画笔
     */
    private Paint paintFill;

    /**
     * 圆环直径
     * 重点：圆环的直径 = 2 * radiusArc + stockWidthArc
     * 即：空心圆直径 + 线宽，而不是  空心圆直径 + 2 * 线宽
     */
    private int diaArc;
    /**
     * 圆环中空心圆的半径
     */
    private float radiusArc;
    /**
     * 圆环中线宽
     */
    private float stockWidthArc;
    /**
     * 圆环颜色
     */
    private int colorArc;

    /**
     * 实心半径
     */
    private float radiusFill;

    /**
     * 实心颜色
     */
    private int colorFill;

    /**
     * 圆环间距
     */
    private float space;

    /**
     * 选中的position
     */
    private int selectPosition;

    public PageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        //  圆环画笔/空心/抗锯齿
        paintArc = new Paint();
        paintArc.setStyle(Paint.Style.STROKE);
        paintArc.setAntiAlias(true);

        //  实心画笔/抗锯齿
        paintFill = new Paint();
        paintFill.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.pagerIndicator);

        radiusArc = typedArray.getDimension(R.styleable.pagerIndicator_radiusArc, 6);
        stockWidthArc = typedArray.getDimension(R.styleable.pagerIndicator_stockWidthArc, 1);
        colorArc = typedArray.getColor(R.styleable.pagerIndicator_colorArc, Color.WHITE);
        radiusFill = typedArray.getDimension(R.styleable.pagerIndicator_radiusFill, 3);
        colorFill = typedArray.getColor(R.styleable.pagerIndicator_colorFill, Color.WHITE);
        count = typedArray.getInteger(R.styleable.pagerIndicator_count, 1);
        space = typedArray.getDimension(R.styleable.pagerIndicator_radiusFill, 10);
        typedArray.recycle();

        //  使用工具函数，将dp 转为 px
        translateSize(radiusArc, stockWidthArc, radiusFill, space);
        //  使用工具函数，给画笔设置属性
        setPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize;

        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        //  圆环直径，这里是个重点，一定是 2 * 内圆的半径 + 线宽，而不是  2 * 内圆的半径 + 2 * 线宽
        diaArc = (int) (2 * radiusArc + stockWidthArc);
        //  指示器总宽度
        int contentWidth = count * diaArc + (int) ((count - 1) * space);
        //  如果是 wrap_content 则设置最小宽高
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = contentWidth + paddingLeft + paddingRight;
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = diaArc + paddingTop + paddingBottom;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 先绘制圆环
        for (int i = 0; i < count; i++) {
            float cx = (i + 0.5F) * diaArc + getPaddingLeft() + i * space;
            float cy = diaArc / 2 + getPaddingTop();
            canvas.drawCircle(cx, cy, radiusArc, paintArc);
        }

        //  绘制实心圆
        float cx = (selectPosition + 0.5F) * diaArc + getPaddingLeft() + selectPosition * space;
        float cy = diaArc / 2 + getPaddingTop();
        canvas.drawCircle(cx, cy, radiusFill, paintFill);
    }


    /**
     * 设置圆环/实心圆
     *
     * @param radiusArc     圆环半径
     * @param widthArc      圆环宽度
     * @param colorArc      圆环颜色
     * @param radiusFill    实心半径
     * @param colorFill     实心颜色
     * @param space         圆环间距
     * @param requestLayout 重新测量还是重新绘制，首先，重绘是肯定的。
     *                      关于重新测量，比如 一开始使用的默认，那么整体布局高度是 11dp，
     *                      如果你那边想要提高或者缩小这个高度，又使用的是 wrap_content，
     *                      那么久需要进行重新测量
     */
    public void setIndicator(float radiusArc, float widthArc, @ColorInt int colorArc,
                             float radiusFill, @ColorInt int colorFill,
                             float space, boolean requestLayout) {
        translateSize(radiusArc, widthArc, radiusFill, space);
        this.colorArc = colorArc;
        this.colorFill = colorFill;
        setPaint();
        //  重绘
        if (requestLayout) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    /**
     * 设置ViewPager
     *
     * @param viewPager
     * @param requestLayout 是否需要重新onMeasure，
     *                      比如，你未设置指示器个数，那么默认就是 1个，
     *                      这时候设置ViewPager，也只是显示1个指示器
     *                      所以可以通过 requestLayout() 来重新 onMeasure
     */
    public void setViewPager(ViewPager viewPager, boolean requestLayout) {
        mViewPager = viewPager;
        mAdapter = viewPager.getAdapter();
        mViewPager.addOnPageChangeListener(mPagerListener);
        count = mAdapter.getCount();
        if (requestLayout) {
            requestLayout();
        }
    }

    public void release(){
        if(mViewPager!=null&&mPagerListener!=null){
            mViewPager.removeOnPageChangeListener(mPagerListener);
            mViewPager=null;
            mPagerListener=null;
        }
        if(mAdapter!=null){
            mAdapter=null;
        }
    }

    private ViewPager.OnPageChangeListener mPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            selectPosition = position;
            invalidate();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    ///////////////////////////////     工具函数    ///////////////////////////////

    /**
     * 将dp 转换为 px
     *
     * @param radiusArc  圆环内 空心圆半径
     * @param widthArc   圆环线宽
     * @param radiusFill 实心圆半径
     * @param space      圆环间距
     */
    private void translateSize(float radiusArc, float widthArc, float radiusFill, float space) {
        this.radiusArc = DisplayUtil.dp2px(radiusArc);
        this.stockWidthArc = DisplayUtil.dp2px(widthArc);
        this.radiusFill = DisplayUtil.dp2px(radiusFill);
        this.space = DisplayUtil.dp2px(space);
    }

    /**
     * 画笔设置属性
     */
    private void setPaint() {
        paintArc.setColor(colorArc);
        paintArc.setStrokeWidth(stockWidthArc);
        paintFill.setColor(colorFill);
    }
}

