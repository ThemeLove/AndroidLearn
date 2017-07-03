package com.themelove.androidlearn.Case.pagertab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.themelove.androidlearn.base.Title;
import com.themelove.androidlearn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingshanliao on 2017/2/16.
 */

/**
 * 本类是测试PagerTabStrip的使用测试类
 */
public class PagerTabStripActivity extends AppCompatActivity {
    private List<View> mViews;
    private List<Title> mTitles;


    private ViewPager mPager;
    private PagerTabStrip mPagerTab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagertabstrip);
        initData();
        initView();
    }

    private void initData() {
        mViews=new ArrayList();
        for (int i=1;i<5;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_share);
            mViews.add(imageView);
        }

        mTitles=new ArrayList();

        for (int i=1;i<5;i++){
            Title title = new Title("第" + i + "页");
            mTitles.add(title);
        }

    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerTab = (PagerTabStrip) findViewById(R.id.pagerTab);
        mPagerTab.setTabIndicatorColor(Color.BLUE);

        mPager.setAdapter(new TabPagerAdapter(mViews,mTitles));
    }
}
