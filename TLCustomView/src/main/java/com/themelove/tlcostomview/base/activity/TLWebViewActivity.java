package com.themelove.tlcostomview.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.themelove.tlcostomview.base.view.TLWebView;

/**
 * Created by qingshanliao on 2017/7/3.
 */

public class TLWebViewActivity extends TLActivity {

    public static final String LOAD_URL = "load_url";

    private String mLoadUrl = "";
    private TLWebView mTLWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mTLWebView = new TLWebView(this);

        mTLWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("TLWebViewActivity", url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mTLWebView.setWebChromeClient(new WebChromeClient());
        mTLWebView.loadUrl(mLoadUrl);
        setContentView(mTLWebView, layoutParams);
    }

    private void initData() {
        if (getIntent().hasExtra(LOAD_URL)) {
            mLoadUrl = (String) getIntent().getExtras().get(LOAD_URL);
        }
    }

    @Override
    public void onBackPressed() {
        if (mTLWebView.canGoBack()) {
            mTLWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public static void start(Activity activity, String url) {
        Intent intent = new Intent(activity, TLWebViewActivity.class);
        intent.putExtra(LOAD_URL, url);
        activity.startActivity(intent);
    }

}
