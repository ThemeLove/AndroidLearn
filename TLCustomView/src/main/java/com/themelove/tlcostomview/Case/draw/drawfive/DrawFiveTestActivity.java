package com.themelove.tlcostomview.Case.draw.drawfive;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLCaseActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

/**
 *
 * Created by qingshanliao on 2017/7/31.
 */
public class DrawFiveTestActivity extends TLCaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawfive);
    }

    @Override
    public void loadBlogOfGod() {
        TLWebViewActivity.start(this, "http://blog.csdn.net/harvic880925/article/details/50423762");
    }

    @Override
    public void loadBlogOfMy() {
        TLWebViewActivity.start(this, mBlogOfMy);
    }

    @Override
    public void showNormalApi() {
        StringBuffer sb = new StringBuffer();
        sb.append("topY=baseLineY+top").append("\n")
                .append("ascentY=baseLineY+ascent").append("\n")
                .append("descentY=baseLineY+descent").append("\n")
                .append("bottomY=baseLineY+bottom").append("\n")
                .append("获取文字绘制宽高：").append("\n")
                .append("宽=paint.measureText()").append("\n")
                .append("高=bootom-top").append("\n")
                .append("获取文字最小区域宽高和坐标：").append("\n")
                .append("宽=paint.getTextBounds().right-paint.getTextBounds().left").append("\n")
                .append("高=paint.getTextBounds().bottom-paint.getTextBounds().top").append("\n")
                .append("最小矩形区域坐标：").append("\n")
                .append("new Rect(getTextBounds().left,getTextBounds().top+baseLineY,getTextBounds().right,getTextBounds().bottom+baseLineY)");


        showMessageDialog("常用Api",sb.toString());
    }
}
