package com.themelove.androidlearn.main;

/**
 * Created by qingshanliao on 2017/2/16.
 */

import com.themelove.androidlearn.base.Title;

/**
 * 封装Title的JavaBean
 */
public class MainTitle extends Title {
    private int    resId;
    private int    titleColor;
    private int    titleSize;

    public MainTitle(String title, int resId, int titleColor, int titleSize) {
        super(title);
        this.resId = resId;
        this.titleColor = titleColor;
        this.titleSize = titleSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }
}
