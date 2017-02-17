package com.themelove.androidlearn.main;

/**
 * Created by qingshanliao on 2017/2/17.
 */

/**
 * Case的javaBean
 */
public class CaseBean {
    public String title;
    public int    resId;


    public CaseBean(String title, int resId) {
        this.title = title;
        this.resId = resId;
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
}
