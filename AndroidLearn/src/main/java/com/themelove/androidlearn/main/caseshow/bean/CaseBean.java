package com.themelove.androidlearn.main.caseshow.bean;

/**
 * Created by qingshanliao on 2017/2/17.
 */

/**
 * Case的javaBean
 */
public class CaseBean {
    private String title;
    private String desc;
    private int    resId;
    /**
     * 跳转到Activity的全类名
     */
    public String    toClass;

    public String getToClass() {
        return toClass;
    }

    public void setToClass(String toClass) {
        this.toClass = toClass;
    }

    public CaseBean(String title, String desc, int resId, String toClass) {
        this.title = title;
        this.desc = desc;
        this.resId = resId;
        this.toClass = toClass;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
