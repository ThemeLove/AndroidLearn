package com.themelove.androidlearn.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.TextView;

import com.themelove.androidlearn.base.TLActivity;
import com.themelove.androidlearn.core.views.WebDialog;

public class DialogTestActivity extends TLActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setBackgroundColor(Color.CYAN);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10,10,10,10);
        textView.setText("点击显示WebDialog");
        textView.setOnClickListener(v ->{
            WebDialog webDialog = new WebDialog(DialogTestActivity.this);
            webDialog.loadUrl("http://www.baidu.com");
        });

        setContentView(textView);
    }
}
