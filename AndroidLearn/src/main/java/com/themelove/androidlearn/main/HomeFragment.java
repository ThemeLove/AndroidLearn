package com.themelove.androidlearn.main;

import android.graphics.Color;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.themelove.androidlearn.AppBase.BaseFragment;

/**
 * Created by qingshanliao on 2017/2/16.
 */

/**
 * 首页Fragment
 */
public class HomeFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
         textView =new TextView(getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
        textView.setTextColor(Color.CYAN);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @Override
    public void initData(final LoadingListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onLoadResult();
            }
        }, 1000);
//       new Timer().schedule(new TimerTask() {
//           @Override
//           public void run() {
//               listener.onLoadResult();
//           }
//       },1000);
    }

    @Override
    public void setData() {
            setState(State.ERROR);
            textView.setText("首页");
    }
}
