package com.themelove.tlcostomview.main;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lqs on 2017/6/8.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragmentList;
    private String[]            mTitles;
    public TabFragmentAdapter(FragmentManager fm ,ArrayList<Fragment> fragmentList,String[] titles) {
        super(fm);
        mFragmentList=fragmentList;
        mTitles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("getItem","fragment创建了"+position);
        if(mFragmentList==null) throw new RuntimeException("fragments is no set");
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if(mFragmentList==null)return 0;
        return mFragmentList.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        Log.i("destoryItem","fragmen销毁了"+position);
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String mTitle = mTitles[position];
        Log.i("getpageTitle","position:"+position);
        SpannableStringBuilder spanBuilder = new SpannableStringBuilder(mTitle);

        if(position==mCurrentPosition){
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
            spanBuilder.setSpan(foregroundColorSpan,0,mTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(45);
            spanBuilder.setSpan(sizeSpan,0,mTitle.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLUE);
            spanBuilder.setSpan(foregroundColorSpan,0,mTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spanBuilder;
    }

    private int mCurrentPosition;

    public void setCurrentItemPosition(int position){
        mCurrentPosition=position;
    }
}
