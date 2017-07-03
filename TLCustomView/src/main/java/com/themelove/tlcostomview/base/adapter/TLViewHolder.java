package com.themelove.tlcostomview.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lqs on 2017/7/2.
 */

public abstract class TLViewHolder extends RecyclerView.ViewHolder{
    public TLViewHolder(View itemView) {
        super(itemView);
    }
    public abstract  void setData();
}
