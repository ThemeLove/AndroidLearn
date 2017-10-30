package com.themelove.tlcostomview.Case.draw.drawsix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.activity.TLCaseActivity;
import com.themelove.tlcostomview.base.activity.TLWebViewActivity;

/**
 * Created by qingshanliao on 2017/8/1.
 */

public class DrawSixTestActivity extends TLCaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawsix);

        View resetCanvas=findViewById(R.id.resetCanvas);
        final GestureTrackView gestureTrackView= (GestureTrackView) findViewById(R.id.gestureTrackView);
        resetCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestureTrackView.resetCanvas();
            }
        });
    }

    @Override
    public void loadBlogOfGod() {
        TLWebViewActivity.start(this,"http://blog.csdn.net/harvic880925/article/details/50995587");
    }

    @Override
    public void loadBlogOfMy() {
        TLWebViewActivity.start(this,"http://blog.csdn.net/themelove");
    }

    @Override
    public void showNormalApi() {
        showMessageDialog("常用Api","");
    }
}
