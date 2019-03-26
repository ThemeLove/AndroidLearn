package com.themelove.androidlearn.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * 自定义全局异常处理
 * Created by qingshanliao on 2017/11/13.
 */
public class TLUnCaughExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final String TAG=TLUnCaughExceptionHandler.class.getSimpleName();
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private TLUnCaughExceptionHandler(){ }
    private static TLUnCaughExceptionHandler instance;
    public  static TLUnCaughExceptionHandler getInstance(){
        if (instance==null){
            synchronized (TLUnCaughExceptionHandler.class){
                if (instance==null){
                    instance=new TLUnCaughExceptionHandler();
                }
            }
        }
        return instance;
    }
    private Context mContext;

    public void init(Context context){
        this.mContext=context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Looper.loop();
                    }catch (Exception e){
                        Log.i("TL","捕获了主线程的异常 e="+e.getMessage());
                    }
                }
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(this);
        Log.i(TAG,"handler init");
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.i("TL","thread-inner="+thread.getName());
        if (!handlerException(thread,ex)&&mDefaultHandler!=null){
            mDefaultHandler.uncaughtException(thread,ex);
            Log.i(TAG,"handler system");
        }else{
//            TODO
            try {
                Thread.sleep(3000);
                Log.i(TAG,"handler sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.i(TAG,"handler e");
            }
            Log.i(TAG,"handler diy");
        }
    }

    private boolean handlerException(Thread thread,Throwable ex){
        if (ex==null){
            return false;
        }
//        TODO
        return true;
    }
}
