package com.themelove.tlcostomview.Case.draw.drawone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLCaseActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

/**
 * 基本自定义控件测试类
 * Created by qingshanliao on 2017/7/3.
 */
public class DrawOneTestActivity extends TLCaseActivity {
    private static final String mBlogOfGod = "http://blog.csdn.net/harvic880925/article/details/38875149";
    private static final String mBlogOfMy  = "http://blog.csdn.net/themelove";
    private static final String mNormalApi = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawone);
        ViewGroup drawContainer = (ViewGroup) findViewById(R.id.drawoneContainer);
//
//        PaintFillView paintFillView = new PaintFillView(this);
//        drawContainer.addView(paintFillView);


    }

    @Override
    public void loadBlogOfGod() {
        TLWebViewActivity.start(DrawOneTestActivity.this, mBlogOfGod);
    }

    @Override
    public void loadBlogOfMy() {
        TLWebViewActivity.start(DrawOneTestActivity.this, mBlogOfMy);
    }

    @Override
    public void showNormalApi() {
        showMessageDialog("常用Api", mNormalApi);
    }
}
