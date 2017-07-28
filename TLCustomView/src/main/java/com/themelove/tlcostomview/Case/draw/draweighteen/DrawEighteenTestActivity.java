package com.themelove.tlcostomview.Case.draw.draweighteen;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLCaseActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

/**
 * Created by qingshanliao on 2017/7/28.
 */
public class DrawEighteenTestActivity extends TLCaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draweighteen);
    }

    @Override
    public void loadBlogOfGod() {

        TLWebViewActivity.start(this,"http://blog.csdn.net/harvic880925/article/details/52039081");
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
