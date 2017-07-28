package com.themelove.tlcostomview.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.themelove.tlcostomview.R;

import java.util.ArrayList;

/**
 * Created by lqs on 2017/6/8.
 */
public class MainActivity extends AppCompatActivity {

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
                 for (int i = 0; i <tab.getTabCount(); i++) {
                     tab.getTabAt(i).setText(tabFragmentAdapter.getPageTitle(i));
                 }
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
            mTitles[i]=i+"";
            Fragment fragment = CaseFragment.getInstance(i);
            mFragmentList.add(fragment);
        }
    }
}
