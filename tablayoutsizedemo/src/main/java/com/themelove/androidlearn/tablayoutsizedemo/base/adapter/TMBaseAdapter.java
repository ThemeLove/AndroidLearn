package com.themelove.androidlearn.tablayoutsizedemo.base.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by lqs on 2017/7/2.
 */

public abstract  class TMBaseAdapter<T extends TMViewHolder> extends RecyclerView.Adapter<T> {
    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setData();
    }
}
