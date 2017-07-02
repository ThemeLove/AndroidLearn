package com.themelove.androidlearn.tablayoutsizedemo.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lqs on 2017/7/2.
 */

public abstract class TMViewHolder extends RecyclerView.ViewHolder{
    public TMViewHolder(View itemView) {
        super(itemView);
    }
    public abstract  void setData();
}
