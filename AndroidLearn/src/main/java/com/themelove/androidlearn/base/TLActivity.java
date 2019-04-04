package com.themelove.androidlearn.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.themelove.androidlearn.utils.DisplayUtil;
import com.themelove.androidlearn.utils.TipUtil;

/**
 * author:qingshanliao
 * date:2018/1/26
 */

public class TLActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayUtil.initScreen(this);
        TipUtil.init(this);
    }

    public void showMessageDialog(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("知道了", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog =builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void showCommitMessageDialog(CharSequence title, CharSequence message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定",(dialog, which) ->dialog.dismiss())
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }

    public void showCommitMessageDialog(CharSequence title, CharSequence message, CharSequence commitTitle, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(commitTitle,(dialog, which) -> {
                    if (listener!=null){
                        listener.onClick(dialog,which);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
