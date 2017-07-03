package com.themelove.tlcostomview.main.bean;

/**
 * Created by lqs on 2017/7/2.
 */

public class ListItem {
    private String title;
    private String subTitle;

    public ListItem(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
