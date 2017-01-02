package com.themelove.androidlearn;

/**
 * Created by lqs on 2016/12/31.
 */

public class VoiceBean {
    private String url;                 //音乐地址url
    private VoiceState voiceState;     //播放状态

    public VoiceBean(String url, VoiceState voiceState) {
        this.url = url;
        this.voiceState = voiceState;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VoiceState getVoiceState() {
        return voiceState;
    }

    public void setVoiceState(VoiceState voiceState) {
        this.voiceState = voiceState;
    }
}
