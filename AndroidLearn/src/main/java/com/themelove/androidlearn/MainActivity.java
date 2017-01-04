package com.themelove.androidlearn;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<VoiceBean> mVoiceBeanList;

    private String url="http://cuotiben-mp3.qiniudn.com/XYA07491.mp3";
    Button play,pause,stop;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        if(mVoiceBeanList==null){
           mVoiceBeanList=new ArrayList<VoiceBean>();
        }
        for (int i=0;i<=20;i++){
            mVoiceBeanList.add(new VoiceBean(url,VoiceState.noStart));
        }

         player = new MediaPlayer();
         player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
             @Override
             public void onPrepared(MediaPlayer mp) {
                player.start();
             }
         });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

            }
        });

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                return false;
            }
        });
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepareAsync();
//            player.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        ListView lv = (ListView) findViewById(R.id.lv);
        final VoiceAdapter voiceAdapter = new VoiceAdapter(this, mVoiceBeanList);
        voiceAdapter.setOnVoiceClickListener(new VoiceAdapter.OnVoiceClickListener() {
            @Override
            public void onClickListener(int position) {

//              重新构造数据
                for (int i = 0; i < mVoiceBeanList.size(); i++) {
                    VoiceBean voiceBean = mVoiceBeanList.get(i);
                    VoiceState voiceState = voiceBean.getVoiceState();
                    if (i == position) {
                        if (voiceState == VoiceState.noStart) {
                            voiceBean.setVoiceState(VoiceState.running);
                        } else if (voiceState == VoiceState.running) {
                            voiceBean.setVoiceState(VoiceState.pause);
                        } else if (voiceState == VoiceState.pause) {
                            voiceBean.setVoiceState(VoiceState.continuePlay);
                        } else if (voiceState == VoiceState.continuePlay) {
                            voiceBean.setVoiceState(VoiceState.pause);
                        }
                    } else {
                        voiceBean.setVoiceState(VoiceState.noStart);
                    }
                }

                voiceAdapter.setDataAndRefresh(mVoiceBeanList);

            }
        });
        lv.setAdapter(voiceAdapter);


        //三个测试按钮
         play = (Button) findViewById(R.id.play);
         pause = (Button) findViewById(R.id.pause);
         stop = (Button) findViewById(R.id.stop);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });

    }
}
