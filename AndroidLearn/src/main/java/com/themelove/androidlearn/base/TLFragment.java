package com.themelove.androidlearn.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.themelove.androidlearn.R;

/**
 * Created by qingshanliao on 2017/2/16.
 */
public abstract class TLFragment extends Fragment {

    private View emptyView;
    private View loadingView;
    private View errorView;
    private View btnReload;

    private State currentState=State.LOADING;
    private View successView;

    /**
     * 加上final,不让子类复写
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Log.i("OnCreateView","onCreateView");

        currentState=State.LOADING;
        ViewGroup viewContainer = (ViewGroup) inflater.inflate(R.layout.layout_container, container, false);
        emptyView = viewContainer.findViewById(R.id.layout_empty);
        loadingView = viewContainer.findViewById(R.id.layout_loading);
        errorView = viewContainer.findViewById(R.id.layout_error);
        btnReload = viewContainer.findViewById(R.id.btn_reload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState=State.LOADING;
                refreshData();
            }
        });
        successView = initView();
        updatePage();
        viewContainer.addView(successView);
        refreshData();
        return viewContainer;
    }

    public void refreshData(){
        updatePage();
        initData(new LoadingListener() {
            @Override
            public void onLoadResult() {
                setData();
                updatePage();
            }
        });
    }


    public State getState(){
        return currentState;
    }

    public void setState(State state){
        currentState=state;
    }

    /**
     * 子类必须重写的创建View视图的方法
     * @return
     */
    public abstract View initView();

    /**
     * 子类必须重写的加载数据的方法，之后根据数据更新界面
     * @param listener
     */
    public abstract void initData(LoadingListener listener);

    public abstract void setData();


    /**
     * 根据状态来更新视图
     */
    private void updatePage() {
        emptyView.setVisibility(currentState==State.EMPTY?View.VISIBLE:View.INVISIBLE);
        loadingView.setVisibility(currentState==State.LOADING?View.VISIBLE:View.INVISIBLE);
        errorView.setVisibility(currentState==State.ERROR?View.VISIBLE:View.INVISIBLE);
        successView.setVisibility(currentState==State.SUCCESS?View.VISIBLE:View.INVISIBLE);
    }

    public interface LoadingListener {
        /**
         * 加载数据完成的监听
         */
        void onLoadResult();
    }

    public enum State {
        LOADING,
        SUCCESS,
        EMPTY,
        ERROR
    }
}
