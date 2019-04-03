package com.themelove.androidlearn.demo.pageindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.TLActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author:qingshanliao
 * date:2019/4/2
 */
public class PageIndicatorTestActivity extends TLActivity {
    private final String TAG=PageIndicatorTestActivity.class.getSimpleName();
    ViewPager mViewPager;

    PageIndicator mIndicator;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //这行去掉标题栏无效，要用下面的代码
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pageindicator);
        initData();
        initView();
    }
    boolean isPause;

    @Override
    protected void onPause() {
        super.onPause();
        isPause=true;
    }

    private void initData() {
        mFragmentList=new ArrayList<>();
        for (int i=0; i<4; i++){
            GuideFragment guideFragment = new GuideFragment();

            if(i==3){
                Bundle bundle = new Bundle();
                guideFragment.setArguments(bundle);
            }
            mFragmentList.add(guideFragment);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!isPause){
            updateFragmentAnim();
        }
    }

    private void initView() {
        mViewPager =findViewById(R.id.viewpager);
        mIndicator =findViewById(R.id.indicator);

        mViewPager.setAdapter(new IndicatorPageAdapter(getSupportFragmentManager(),mFragmentList));
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(mFragmentList.size()-1);
        mIndicator.setViewPager(mViewPager,true);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.i(TAG,"onPageScrolled----->i="+i+",v="+v+",il="+i1);
            }
            @Override
            public void onPageSelected(int i) {
                Log.i(TAG,"onPageSelectd--i="+i);
                currentPosition=i;
                updateFragmentAnim();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG,"onPageScrollStateChanged----->state="+state);
                if (state==ViewPager.SCROLL_STATE_DRAGGING){ //滑动状态
                    isDragging=true;
                    updateFragmentAnim();
                }else if(state==ViewPager.SCROLL_STATE_IDLE){//闲置状态
                    isDragging=false;
                    updateFragmentAnim();
                }
            }
        });
    }

    int currentPosition=0;
    boolean isDragging=false;
    private void updateFragmentAnim(){
        for (int i=0;i<mFragmentList.size();i++){
            ((GuideFragment)mFragmentList.get(i)).stopAnim();
        }
        if (!isDragging)((GuideFragment)mFragmentList.get(currentPosition)).startAnim();
    }

}
