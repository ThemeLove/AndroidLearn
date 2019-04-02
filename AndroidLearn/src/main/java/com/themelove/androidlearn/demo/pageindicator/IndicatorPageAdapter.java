package com.themelove.androidlearn.demo.pageindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * author:qingshanliao
 * date:2019/4/2
 */
public class IndicatorPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public IndicatorPageAdapter(FragmentManager fm,List<Fragment> fragmentList){
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        if(fragmentList!=null){
            return fragmentList.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(fragmentList!=null){
            return fragmentList.size();
        }
        return 0;
    }
}
