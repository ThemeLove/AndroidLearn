package com.themelove.androidlearn;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lqs on 2016/12/31.
 */

public class AudioAdapter extends BaseAdapter{
    private Context mContext;
    private List<AudioBean> mAudioBeanList;
    private LayoutInflater mInflater;
    private OnVoiceClickListener mListener;
    public AudioAdapter(Context context, List<AudioBean> audioBeanList){
        mContext=context;
        mAudioBeanList = audioBeanList;
        mInflater=LayoutInflater.from(mContext);
    }

    public void setOnVoiceClickListener(OnVoiceClickListener listener){
        mListener=listener;
    }

    public void setDataAndRefresh(List<AudioBean> audioBeanList){
        mAudioBeanList = audioBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAudioBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAudioBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AudioBean audioBean = (AudioBean) getItem(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder(audioBean);
            convertView=mInflater.inflate(R.layout.item_voice,parent,false);
            holder.mRlVoice= (RelativeLayout) convertView.findViewById(R.id.rl_voice);
            holder.mPlayBtn= (ImageView) convertView.findViewById(R.id.iv_voice);
            holder.mDuration = (TextView) convertView.findViewById(R.id.tv_duration);
            AnimationDrawable anim = (AnimationDrawable) holder.mPlayBtn.getDrawable();
            holder.anim=anim;
            convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();
        holder.mDuration.setText("3'30__"+position);
        if(audioBean.getAudioState()== AudioState.inited){//第一次的初始化完成状态
            holder.mPlayer.reset();
        }else if(audioBean.getAudioState()== AudioState.started){
            holder.mPlayer.play();
            holder.anim.start();
        }else if(audioBean.getAudioState()== AudioState.paused){
            holder.mPlayer.pause();
            holder.anim.start();
        }else if(audioBean.getAudioState()== AudioState.stopped){
            holder.mPlayer.stop();
            holder.anim.stop();
        }

        holder.mRlVoice.setOnClickListener(new View.OnClickListener() {
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
        private AudioBean mAudioBean;
        private AudioPlayer mPlayer;
        private RelativeLayout mRlVoice;
        private ImageView mPlayBtn;
        private TextView  mDuration;
        private AnimationDrawable anim;


        public ViewHolder(AudioBean audioBean){
            mAudioBean = audioBean;
            mPlayer=new AudioPlayer(mAudioBean.getUrl());
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
