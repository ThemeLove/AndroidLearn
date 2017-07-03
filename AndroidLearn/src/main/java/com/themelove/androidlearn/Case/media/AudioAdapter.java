package com.themelove.androidlearn.Case.media;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.themelove.androidlearn.R;

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
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_voice,parent,false);
            holder.mRlVoice= (RelativeLayout) convertView.findViewById(R.id.rl_voice);
            holder.mDuration= (TextView) convertView.findViewById(R.id.tv_duration);
            holder.mVoiceIv= (ImageView) convertView.findViewById(R.id.iv_voice);
            convertView.setTag(holder);
        }

        holder= (ViewHolder) convertView.getTag();
        AudioState audioState = audioBean.getAudioState();
        holder.mVoiceIv.setBackgroundResource(R.drawable.voice_anim);
        AnimationDrawable anim = (AnimationDrawable) holder.mVoiceIv.getBackground();

        if (audioState== AudioState.inited){
            holder.mDuration.setText("初始化状态");
            if(anim.isRunning()){
                anim.stop();
                anim.selectDrawable(0);
            }
        }else if(audioState== AudioState.started){
//            if (!anim.isRunning()){
//                anim.start();
//            }
            anim.run();
            holder.mDuration.setText("正在播放");
        }else if(audioState== AudioState.paused){
            if(anim.isRunning()){
                anim.stop();
            }
            holder.mDuration.setText("暂停状态");
        }else if(audioState== AudioState.stopped){
            if(anim.isRunning()){
                anim.stop();
                anim.selectDrawable(0);
            }
            holder.mDuration.setText("播放完成");
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
        private ImageView mVoiceIv;
        private TextView  mDuration;
        private AnimationDrawable anim;
    }

    public interface OnVoiceClickListener{
        void onClickListener(int position);
    }


}
