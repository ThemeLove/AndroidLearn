package com.themelove.androidlearn.demo.progressbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.TLActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * author:qingshanliao
 * date:2019/6/4
 */
public class HorizontalProgressBarActivity extends TLActivity {

    private ProgressBar pb;
    private Button step1;
    private Button step2;
    private Button step3;
    private Button init;
    private Button finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_progressbar);
        initView();
        addListener();
    }

    private void initView() {
        pb = findViewByID(R.id.pb);
        init = findViewByID(R.id.init);
        step1 = findViewByID(R.id.step1);
        step2 = findViewByID(R.id.step2);
        step3 = findViewByID(R.id.step3);
        finish = findViewByID(R.id.finish);
    }

    private int mCurrentProgress=0;
    private int mTargetProgress=0;

    private Timer mTimer = new Timer();

    private void addListener() {
        init.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                toProgress(0);
            }
        });
        step1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toProgress(25);
            }
        });

        step2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toProgress(50);
            }
        });

        step3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toProgress(75);
            }
        });

        finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                toProgress(100);
            }
        });

    }


    private void toProgress(int targetProgress){
        mTargetProgress=targetProgress;
        TimerTask timerTask=new TimerTask(){
            @Override
            public void run() {
                mCurrentProgress=pb.getProgress();
                if (mCurrentProgress==mTargetProgress){
                    cancel();
                    return;
                }
                if (mCurrentProgress<mTargetProgress){
                    pb.setProgress(++mCurrentProgress);
                }else{
                    pb.setProgress(--mCurrentProgress);
                }
            }
        };
        mTimer.schedule(timerTask,50,50);
    }
}
