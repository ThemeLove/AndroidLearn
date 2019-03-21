package com.themelove.androidlearn.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * 提示工具类
 * author:qingshanliao
 * date:2018/1/29
 */
public class TipUtil {
    private static Context mContext;
    private static Toast toastInstance=null;

    public static void init(Context context){
        mContext=context;
    }

    /**
     * 文字的单例Toast
     * @param message
     */
    public static void showToast(String message){
        if (toastInstance==null){
            toastInstance=Toast.makeText(mContext,message,Toast.LENGTH_SHORT);
        }
        toastInstance.setText(message);
        toastInstance.setGravity(Gravity.CENTER,0,0);
        toastInstance.setDuration(Toast.LENGTH_SHORT);
        toastInstance.show();
    }

    /**
     * 自定义View的单例Toast
     * @param view
     */
    public static void showToast(View view){
        if (toastInstance==null){
            toastInstance=Toast.makeText(mContext,"",Toast.LENGTH_SHORT);
        }
        toastInstance.setView(view);
        toastInstance.setDuration(Toast.LENGTH_SHORT);
        toastInstance.show();
    }

}
