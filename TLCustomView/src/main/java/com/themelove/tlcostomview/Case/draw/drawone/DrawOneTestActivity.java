package com.themelove.tlcostomview.Case.draw.drawone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

/**
 * Created by qingshanliao on 2017/7/3.
 */

public class DrawOneTestActivity extends TLActivity{
    private static final String mBlogOfGod="http://blog.csdn.net/harvic880925/article/details/38875149";
    private static final String mBlogOfMy="http://blog.csdn.net/themelove";
    private static final String mNormalApi="";

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        switch (itemId){
            case R.id.blogOfGod:
                TLWebViewActivity.start(DrawOneTestActivity.this,mBlogOfGod);
                break;
            case R.id.blogOfMy:
                TLWebViewActivity.start(DrawOneTestActivity.this,mBlogOfMy);
                break;
            case R.id.normalApi:
                showMessageDialog("常用Api",mNormalApi);
                break;
        }
        return true;
    }
}
