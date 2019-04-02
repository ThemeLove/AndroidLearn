package com.themelove.androidlearn.demo.pageindicator;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.TLFragment;

/**
 * author:qingshanliao
 * date:2019/4/2
 */
public class GuideFragment extends TLFragment {
    private final String TAG = GuideFragment.class.getSimpleName();
    ImageView iv_anim;
    AnimationDrawable mAnimation;
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_indicator, null, false);
        iv_anim = view.findViewById(R.id.iv_anim);
        mAnimation = (AnimationDrawable) iv_anim.getBackground();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG,"setUserVisibleHint---isVisibleToUser="+isVisibleToUser);
        if (isVisibleToUser){
            /*if (mAnimation!=null&&!mAnimation.isRunning()){
                mAnimation.stop();
                mAnimation.start();
            }else{
                if (mAnimation!=null&&mAnimation.isRunning()){
                    mAnimation.stop();
                }
            }*/
        }
    }

    public void startAnim(){
        Log.i(TAG,"startAnim");
        if (mAnimation!=null){
            mAnimation.stop();
            mAnimation.start();
        }
    }

    public void stopAnim(){
        Log.i(TAG,"stopAnim");
        if (mAnimation!=null){
            mAnimation.stop();
        }
    }

    @Override
    public boolean getUserVisibleHint() {
//        Log.i(TAG,"getUserVisibleHint");
        return super.getUserVisibleHint();
    }

    @Override
    public void initData(LoadingListener listener) {
        Bundle bundle =getArgumentData();
        if (listener!=null){
            listener.onLoadResult();
        }
    }

    @Override
    public void setData() {
        setState(State.SUCCESS);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
        if (mAnimation!=null&&mAnimation.isRunning()){
            Log.i(TAG,"onDestroy-stop");
            mAnimation.stop();
            mAnimation=null;
        }
    }

    @Override
    public void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView");
        if (mAnimation!=null&&mAnimation.isRunning()){
            Log.i(TAG,"onDestroyView--stop");
            mAnimation.stop();
        }
    }
}
