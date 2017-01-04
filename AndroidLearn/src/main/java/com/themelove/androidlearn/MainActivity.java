package com.themelove.androidlearn;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<AudioBean> mAudioBeanList;
    private String url="http://cuotiben-mp3.qiniudn.com/XYA07491.mp3";
    private String path="/assets/NewDay.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        if(mAudioBeanList ==null){
           mAudioBeanList =new ArrayList<AudioBean>();
        }
        for (int i=0;i<=20;i++){
            mAudioBeanList.add(new AudioBean(url, AudioState.inited));
        }


    }

    private void initView() {

        ListView lv = (ListView) findViewById(R.id.lv);

        final AudioAdapter audioAdapter = new AudioAdapter(this, mAudioBeanList);
        audioAdapter.setOnVoiceClickListener(new AudioAdapter.OnVoiceClickListener() {
            @Override
            public void onClickListener(int position) {

//              重新构造数据
                for (int i = 0; i< mAudioBeanList.size(); i++){
                    AudioBean audioBean = mAudioBeanList.get(i);
                    AudioState audioState = audioBean.getAudioState();
                    if (i==position){
                        if (audioState == AudioState.inited){
                            audioBean.setAudioState(AudioState.started);
                        }else if(audioState == AudioState.started){
                            audioBean.setAudioState(AudioState.paused);
                        }else if(audioState == AudioState.paused){
                            audioBean.setAudioState(AudioState.started);
                        }else if(audioState == AudioState.stopped){
                            audioBean.setAudioState(AudioState.started);
                        }
                    }else{
                        audioBean.setAudioState(AudioState.inited);
                    }
                }
            audioAdapter.setDataAndRefresh(mAudioBeanList);
            }
        });
        lv.setAdapter(audioAdapter);
    }

}
