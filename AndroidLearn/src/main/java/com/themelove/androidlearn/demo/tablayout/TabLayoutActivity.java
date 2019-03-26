package com.themelove.androidlearn.demo.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.main.caseshow.ui.CaseFragment;
import com.themelove.androidlearn.main.home.ui.HomeFragment;

import java.util.ArrayList;

/**
 * Created by lqs on 2017/6/8.
 */

public class TabLayoutActivity extends AppCompatActivity {

    private ArrayList<Fragment> mFragmentList;
    private String[] mTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        initData();
        initView();
    }

    private void initView() {
        final TabLayout tab = (TabLayout) findViewById(R.id.tabLayout);
        final ViewPager vp = (ViewPager) findViewById(R.id.vp);
        tab.setupWithViewPager(vp);
        tab.setTabMode(TabLayout.MODE_FIXED);
        final TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), mFragmentList, mTitles);
        vp.setAdapter(tabFragmentAdapter);

         vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {
                 tabFragmentAdapter.setCurrentItemPosition(position);
                 CharSequence pageTitle = tabFragmentAdapter.getPageTitle(position);
                 tab.setupWithViewPager(vp);
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });
    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        mTitles = new String[8];
        for (int i = 0; i < 8; i++) {
            mTitles[i]="第"+i+"页";
            Fragment fragment;
               if(i%2==0){
                   fragment = new HomeFragment();
               }else{
                   fragment = new CaseFragment();
               }
            mFragmentList.add(fragment);
        }
    }
}
