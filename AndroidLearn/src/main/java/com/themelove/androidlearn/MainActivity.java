package com.themelove.androidlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<VoiceBean> mVoiceBeanList;
    private String url="http://cuotiben-mp3.qiniudn.com/XYA07491.mp3";

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
    }

    private void initView() {
        ListView lv = (ListView) findViewById(R.id.lv);
        final VoiceAdapter voiceAdapter = new VoiceAdapter(this, mVoiceBeanList);
        voiceAdapter.setOnVoiceClickListener(new VoiceAdapter.OnVoiceClickListener() {
            @Override
            public void onClickListener(int position) {

//              重新构造数据
                for (int i=0;i<mVoiceBeanList.size();i++){
                    VoiceBean voiceBean = mVoiceBeanList.get(i);
                    VoiceState voiceState = voiceBean.getVoiceState();
                    if (i==position){
                        if (voiceState==VoiceState.noStart){
                            voiceBean.setVoiceState(VoiceState.running);
                        }else if(voiceState==VoiceState.running){
                            voiceBean.setVoiceState(VoiceState.pause);
                        }else if(voiceState==VoiceState.pause){
                            voiceBean.setVoiceState(VoiceState.continuePlay);
                        }else if(voiceState==VoiceState.continuePlay){
                            voiceBean.setVoiceState(VoiceState.pause);
                        }
                    }else{
                        voiceBean.setVoiceState(VoiceState.noStart);
                    }
                }

            voiceAdapter.setDataAndRefresh(mVoiceBeanList);

            }
        });
        lv.setAdapter(voiceAdapter);
    }

}
