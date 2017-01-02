package com.themelove.androidlearn;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 * Created by lqs on 2016/12/31.
 */

public class VoiceAdapter extends BaseAdapter{
    private Context mContext;
    private List<VoiceBean> mVoiceBeanList;
    private LayoutInflater mInflater;
    private OnVoiceClickListener mListener;
    public VoiceAdapter(Context context, List<VoiceBean>voiceBeanList){
        mContext=context;
        mVoiceBeanList=voiceBeanList;
        mInflater=LayoutInflater.from(mContext);
    }

    public void setOnVoiceClickListener(OnVoiceClickListener listener){
        mListener=listener;
    }

    public void setDataAndRefresh(List<VoiceBean> voiceBeanList){
        mVoiceBeanList=voiceBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mVoiceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mVoiceBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        VoiceBean voiceBean = (VoiceBean) getItem(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder(voiceBean);
            convertView=mInflater.inflate(R.layout.item_voice,parent,false);
            holder.mPlayBtn= (Button) convertView.findViewById(R.id.btn_play);
            convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();
        if(voiceBean.getVoiceState()==VoiceState.running){
            holder.mPlayer.play();
            holder.mPlayBtn.setText("正在播放");
            holder.mPlayBtn.setBackgroundColor(Color.YELLOW);
        }else if(voiceBean.getVoiceState()==VoiceState.noStart){
            holder.mPlayer.pause();
            holder.mPlayBtn.setText("开始播放");
            holder.mPlayBtn.setBackgroundColor(Color.BLUE);
        }else if(voiceBean.getVoiceState()==VoiceState.continuePlay){
            holder.mPlayer.continuePlay();
            holder.mPlayBtn.setText("正在播放");
            holder.mPlayBtn.setBackgroundColor(Color.YELLOW);
        }else if(voiceBean.getVoiceState()==VoiceState.pause){
            holder.mPlayer.pause();
            holder.mPlayBtn.setText("暂停中");
            holder.mPlayBtn.setBackgroundColor(Color.RED);
        }else if (voiceBean.getVoiceState()==VoiceState.stop){
            holder.mPlayer.stop();
            holder.mPlayBtn.setText("播放完成");
            holder.mPlayBtn.setBackgroundColor(Color.BLACK);
        }
        holder.mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onClickListener(position);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder{
        private VoiceBean mVoiceBean;
        private ImPlayer mPlayer;
        private Button mPlayBtn;


        public ViewHolder(VoiceBean voiceBean){
            mVoiceBean=voiceBean;
            mPlayer=new ImPlayer(mVoiceBean.getUrl());
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mPlayer.play();
                }
            });
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
        }


    }

    public interface OnVoiceClickListener{
        void onClickListener(int position);
    }


}
