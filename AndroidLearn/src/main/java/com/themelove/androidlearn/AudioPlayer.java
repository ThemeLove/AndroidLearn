package com.themelove.androidlearn;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * 语音播放
 *
 * @author --ZhangJiQiang
 * @date 2015-4-27
 */
public class AudioPlayer implements OnPreparedListener {

        private MediaPlayer mediaPlayer; // 媒体播放器
        private OnCompletionListener mCompletionListener;
        private OnPreparedListener   mPreparedListener;
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

        public void setOnPreparedListener(OnPreparedListener preparedlistener){
            mPreparedListener=preparedlistener;
        }
    //  设置完成监听
        public void setOnCompletionListener(OnCompletionListener completionlistener){
            mCompletionListener=completionlistener;
        }

        public boolean isPlaying() {
            if (mediaPlayer==null)return false;
            return mediaPlayer.isPlaying();
        }

        /**
         * 开始播放
         */
        public void play( ) {
                if (mediaPlayer==null) return;
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

        public void reset(){
            if (mediaPlayer!=null){
                mediaPlayer.reset();
            }
        }

        public void prepare(){
            if (mediaPlayer!=null){
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void prepareAsync(){
            if (mediaPlayer!=null){
                mediaPlayer.prepareAsync();
            }
        }
    /**
     * 继续播放，也是调用MediaPlayer的play()方法，内部自动维护记住上次播放功能
     */
    public void continuePlay(){
            if (mediaPlayer!=null){
                mediaPlayer.start();
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


        /**
         * 停止
         */
        public void release() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

        }

        /**
         * 释放资源
         */
        public void stop() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
        }

    }