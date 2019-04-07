package com.themelove.androidlearn.core.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.themelove.androidlearn.utils.DisplayUtil;

public class WebDialog extends Dialog {
    private final String TAG=WebDialog.class.getSimpleName();
    private Context context;
    private WebView webView;
    private boolean isHorizontal;
    private RelativeLayout containerView;

    public WebDialog(Context context) {
        super(context);
        init(context);
    }

    public WebDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public WebDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        init(context);
    }

    private void init(Context context) {

        this.context=context;
        if (context instanceof Activity){
            isHorizontal = DisplayUtil.isHorizontal((Activity) context);
        }
        Log.i(TAG,"screenWidht="+DisplayUtil.screenWidth);
        Log.i(TAG,"screenHeight="+DisplayUtil.screenHeight);
        Log.i(TAG,"isHorizontal="+isHorizontal);

        initView();

//        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });

        show();

        Window window=getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.gravity= Gravity.CENTER;

        if (isHorizontal){
            windowParams.width=DisplayUtil.screenHeight*4/5;
            windowParams.height=DisplayUtil.screenWidth*4/5;
        }else{
            windowParams.width=DisplayUtil.screenWidth*4/5;
            windowParams.height=DisplayUtil.screenHeight*4/5;
        }

        Log.i(TAG,"windowParams.width="+windowParams.width);
        Log.i(TAG,"windowParams.height="+windowParams.height);
        getWindow().setAttributes(windowParams);
        setContentView(containerView);
    }

    private void initView(){
        containerView = new RelativeLayout(context);
        containerView.setPadding(10,10,10,10);
        containerView.setBackgroundColor(Color.YELLOW);
        RelativeLayout.LayoutParams rlLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(context);
        webView.setBackgroundColor(Color.BLUE);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //设置允许js弹出alert对话框。
        settings.setSupportZoom(true);//是否可以缩放，默认是true
        settings.setBuiltInZoomControls(true);//是否可以显示缩放按钮，默认是false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放，大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    view.loadUrl(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        if (isHorizontal){
            settings.setTextZoom(50); //缩放50
        }else{
            settings.setTextZoom(80); //缩放80
        }
        containerView.addView(webView,rlLayoutParams);
    }

    public void loadUrl(String url){
        webView.loadUrl(url);
    }

}
