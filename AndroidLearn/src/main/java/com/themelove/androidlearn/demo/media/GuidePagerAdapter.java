package com.themelove.androidlearn.demo.media;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jie on 16/9/21.
 * 引导页面的ViewPagerAdapter
 */
public class GuidePagerAdapter extends PagerAdapter {
    private List<View> pages;

    public GuidePagerAdapter(List<View> lists) {
        this.pages = lists;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pages.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pages.get(position), 0);
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

}