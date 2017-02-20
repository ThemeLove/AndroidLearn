package com.themelove.androidlearn.main;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.ViewGroup;

import com.themelove.androidlearn.Utils.BitmapUtil;

import java.util.List;

/**
 * Created by qingshanliao on 2017/2/16.
 */

/**
 * 主界面的Adapter
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>  mFragments;
    private List<MainTitle> mMainTitles;

    public MainPagerAdapter(FragmentManager manager,List<Fragment> fragments,List<MainTitle> mainTitles){
        super(manager);
        mFragments=fragments;
        mMainTitles = mainTitles;
    }
    @Override
    public int getCount() {
        if (mFragments==null)return 0;
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        Log.i("创建了","fragment创建了"+position);
        if (mFragments==null) throw new RuntimeException("the is no fragment set");
        return mFragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("销毁了","fragment销毁了"+position);
        super.destroyItem(container, position, object);
    }

    //通过构造含有图片的span来实现，带图片的标题
    @Override
    public CharSequence getPageTitle(int position) {
        if (mMainTitles ==null|| mMainTitles.size()==0)return "";

        MainTitle mainTitle = mMainTitles.get(position);
        SpannableStringBuilder span = new SpannableStringBuilder(mMainTitles.get(position).getTitle()+"\n图片");
//      字体颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        span.setSpan(foregroundColorSpan,0, mainTitle.getTitle().length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ImageSpan imageSpan = new ImageSpan(BitmapUtil.getBitmapFromRes(mainTitle.getResId()));
        span.setSpan(imageSpan, mainTitle.getTitle().length()+1,span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return span;
    }
}
