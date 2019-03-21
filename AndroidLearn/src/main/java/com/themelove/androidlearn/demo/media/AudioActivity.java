package com.themelove.androidlearn.demo.media;

import android.app.ActionBar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.themelove.androidlearn.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AudioActivity extends AppCompatActivity {

    private List<AudioBean> mAudioBeanList;
//    private String url="http://cuotiben-mp3.qiniudn.com/XYA07491.mp3";
//    private String url="http://cuotiben-mp3.qiniudn.com/7%E6%9C%8824%E6%97%A5%2017%E7%82%B945%E5%88%86.mp3";
//                        http://cuotiben-mp3.qiniudn.com/7%E6%9C%8824%E6%97%A5+17%E7%82%B945%E5%88%86.mp3
//                        http://cuotiben-mp3.qiniudn.com/7%E6%9C%8824%E6%97%A5%2017%E7%82%B945%E5%88%86.mp3
    private String url="http://cuotiben-mp3.qiniudn.com/7月24日 17点45分.mp3";
//    http://cuotiben-mp3.qiniudn.com/7%25E6%259C%258824%25E6%2597%25A5%252017%25E7%2582%25B945%25E5%2588%2586.mp3
    private AudioPlayer mPlayer;
    private AudioBean currentAudioBean;//当前正在播放的音乐对应的AudioBean
    private AudioAdapter audioAdapter;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        TextView textView = new TextView(this);
        textView.setText("What the fuck");
        textView.setBackgroundColor(0xffff0000);
        textView.setHeight(780);
        setContentView(textView);*/
        setContentView(R.layout.activity_audio);
        int length = "http://cuotiben-mp3.qiniudn.com/".length();
        url=url.substring(0,length)+ Uri.encode(url.substring(length));
        Log.i("url",url);
        initData();
        initView();
    }

    private void initData() {
        if(mAudioBeanList ==null){
           mAudioBeanList =new ArrayList<AudioBean>();
        }
        for (int i=0;i<=40;i++){
            mAudioBeanList.add(new AudioBean(url, AudioState.inited));
        }

         mPlayer = new AudioPlayer();

        //准备完毕监听
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPlayer.start();
            }
        });

        //播放完成监听
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                currentAudioBean.setAudioState(AudioState.stopped);
                mPlayer.stop();
                audioAdapter.setDataAndRefresh(mAudioBeanList);
            }
        });

        //缓冲监听
        mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

            }
        });
        //错误监听
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void initView() {
        ActionBar actionBar = getActionBar();
//        actionBar.setLogo(R.mipmap.ic_setting);
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setLogo(R.mipmap.ic_setting);
        supportActionBar.setIcon(R.drawable.voice_anim);/*设置actionbar上面的icon*/
        supportActionBar.setTitle(getString(R.string.app_name));/*设置actionbar上面的name*/
        supportActionBar.setDisplayHomeAsUpEnabled(true);/*显示home按钮*/
        supportActionBar.setDisplayShowHomeEnabled(true);/*设置home按钮可以被点击*/

        ListView lv = (ListView) findViewById(R.id.lv);

        audioAdapter = new AudioAdapter(this, mAudioBeanList);

        audioAdapter.setOnVoiceClickListener(new AudioAdapter.OnVoiceClickListener() {
            @Override
            public void onClickListener(int position) {

                //重置播放器的播放状态
                currentAudioBean = mAudioBeanList.get(position);
                //如果当前点击的条目之前不是播放状态，则重新播放
                if (currentAudioBean.getAudioState()==AudioState.inited){
                    try {
                        mPlayer.reset();
                        mPlayer.getMediaPlayer().setDataSource(currentAudioBean.getUrl());
                        mPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(currentAudioBean.getAudioState()==AudioState.started){//之前是正在播放状态，点击则暂停
                        mPlayer.pause();
                }else if(currentAudioBean.getAudioState()==AudioState.paused){//之前是暂停状态，则点击继续播放
                        mPlayer.start();
                }else if(currentAudioBean.getAudioState()==AudioState.stopped){//如果之前播放完成，则点击重新播放
                        mPlayer.prepareAsync();
                }
//              重新构造数据，用来显示ListView列表的状态
                for (int i = 0; i< mAudioBeanList.size(); i++){
                    AudioBean audioBean = mAudioBeanList.get(i);
                    AudioState audioState = audioBean.getAudioState();

                    //如果是当前点击的条目，根据之前的状态来设置点击之后的状态
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
