package com.themelove.androidlearn.media;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;

import java.io.IOException;

/**
 * 语音播放
 *
 * @author --ZhangJiQiang
 * @date 2015-4-27
 */
public class AudioPlayer implements OnPreparedListener {

    private MediaPlayer mediaPlayer; // 媒体播放器

    // 初始化播放器
    public AudioPlayer( ) {
        super();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     *  设置准备监听
     */
    public void setOnPreparedListener(OnPreparedListener preparedlistener) {
        if (mediaPlayer!=null)mediaPlayer.setOnPreparedListener(preparedlistener);
    }

    /**
     *  设置完成监听
     */
    public void setOnCompletionListener(OnCompletionListener completionlistener) {
        if (mediaPlayer!=null) mediaPlayer.setOnCompletionListener(completionlistener);
    }
    /**
     *  设置缓冲监听
     */
    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener){
        if (mediaPlayer!=null) mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
    }
    /**
     *  设置错误监听
     */
    public void setOnErrorListener(MediaPlayer.OnErrorListener errorListener){
        if (mediaPlayer!=null) mediaPlayer.setOnErrorListener(errorListener);
    }

    public boolean isPlaying() {
        if (mediaPlayer == null) return false;
        return mediaPlayer.isPlaying();
    }

    /**
     * 同步准备
     */
    public void prepare() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 异步准备
     */
    public void prepareAsync() {
        if (mediaPlayer != null) {
            mediaPlayer.prepareAsync();
        }
    }

    /**
     * 开始播放
     */
    public void start() {
        if (mediaPlayer == null) return;
        mediaPlayer.start();
    }

    /**
     * 暂停
     */
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /**
     * 继续播放，也是调用MediaPlayer的play()方法，内部自动维护记住上次播放功能
     */
    public void continuePlay() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }


    /**
     * 让MediaPlayer回到闲置状态
     */
    public void reset() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


    /**
     * 释放资源
     */
    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    /**
     * 在线播放 url
     *
     * @param url
     */
    public void playUrl(String url) {
        System.out.println("播放的url  ==  " + url);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            // mediaPlayer.prepareAsync(); // prepare自动播放
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


    public MediaPlayer getMediaPlayer(){
        if (mediaPlayer!=null){
            return mediaPlayer;
        }
        return null;
    }

}