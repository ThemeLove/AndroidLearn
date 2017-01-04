package com.themelove.androidlearn;

/**
 * Created by lqs on 2016/12/31.
 */

public class AudioBean {
    private String url;                 //音乐地址url
    private AudioState audioState;     //播放状态

    public AudioBean(String url, AudioState audioState) {
        this.url = url;
        this.audioState = audioState;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AudioState getAudioState() {
        return audioState;
    }

    public void setAudioState(AudioState audioState) {
        this.audioState = audioState;
    }
}
