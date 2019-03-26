package com.themelove.androidlearn.demo.progressbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.themelove.androidlearn.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qingshanliao on 2017/6/16.
 */

public class ProgressBarTestActivity extends AppCompatActivity {

    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        final NumberProgressBar progressBar = (NumberProgressBar) findViewById(R.id.progressBar1);
        progressBar.setOnProgressBarListener(new OnProgressBarListener() {
            @Override
            public void onProgressChange(int current, int max) {
                if (current == max) {
                    Toast.makeText(ProgressBarTestActivity.this, "下载完成了", Toast.LENGTH_SHORT).show();
                    progressBar.setProgress(0);
                }
            }
        });


        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.incrementProgressBy(1);
                    }
                });
            }
        };

        timer.schedule(timerTask, 1000, 100);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        finish();
    }
}
