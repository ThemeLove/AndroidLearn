package com.themelove.androidlearn.Case.pagertab;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.Title;
import com.themelove.androidlearn.utils.BitmapUtil;

import java.util.List;

/**
 * Created by qingshanliao on 2017/2/16.
 */

public class TabPagerAdapter extends PagerAdapter{
    private List<View> mViews;
    private List<Title> mTitles;
    public TabPagerAdapter(List<View> views, List<Title> titles){
        mViews=views;
        mTitles=titles;
    }

    @Override
    public int getCount() {
        return mViews==null?0:mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles==null) return "";

        Title title=mTitles.get(position);
        SpannableStringBuilder span = new SpannableStringBuilder(title.getTitle()+"图片");
//      字体颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        span.setSpan(foregroundColorSpan,0, title.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ImageSpan imageSpan = new ImageSpan(BitmapUtil.getBitmapFromRes(R.mipmap.ic_share));
        span.setSpan(imageSpan, title.getTitle().length(),span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }
}
