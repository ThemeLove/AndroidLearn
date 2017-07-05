package com.themelove.tlcostomview.Case.draw.drawfour;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLCaseActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

/**
 * Created by qingshanliao on 2017/7/5.
 */

public class DrawFourTestActivity extends TLCaseActivity {
    private String mBlogOfGod="http://blog.csdn.net/harvic880925/article/details/39080931";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawfour);
    }

    @Override
    public void loadBlogOfGod() {
        TLWebViewActivity.start(this,mBlogOfGod);
    }

    @Override
    public void loadBlogOfMy() {
        TLWebViewActivity.start(this,mBlogOfMy);
    }

    @Override
    public void showNormalApi() {
        showMessageDialog("常用Api","");
    }
}
