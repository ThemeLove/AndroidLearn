package com.themelove.tlcostomview.Case.draw.drawtwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLCaseActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Created by qingshanliao on 2017/7/4.
 */
public class DrawTwoTestActivity extends TLCaseActivity {
    private String mBlogOfGod="http://blog.csdn.net/harvic880925/article/details/38926877";
    private String mNormalApi="";

    private int process=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawtwo);
        final CircleProcessBar circleProcessBar = (CircleProcessBar) findViewById(R.id.circleProcessBar);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        process++;
                        if (process==100)process=0;
                        circleProcessBar.setProcess(process);
                    }
                });

            }
        };

        timer.schedule(timerTask,100,100);

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
