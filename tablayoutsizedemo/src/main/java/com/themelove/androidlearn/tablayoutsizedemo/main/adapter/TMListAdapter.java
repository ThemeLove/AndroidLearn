package com.themelove.androidlearn.tablayoutsizedemo.main.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.themelove.androidlearn.tablayoutsizedemo.base.adapter.TMBaseAdapter;

import java.util.List;

/**
 * Created by lqs on 2017/7/2.
 */

public class TMListAdapter extends TMBaseAdapter<TMTextViewHolder>{

    private List<ListItem>       mListItems;
    private OnItemClickListener  mItemClickListener;
    public TMListAdapter(List<ListItem> listItems,OnItemClickListener itemClickListener){
        mListItems=listItems;
        mItemClickListener=itemClickListener;
    }

    @Override
    public TMTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TMTextViewHolder(parent.getContext(),mItemClickListener,mListItems);
    }

    @Override
    public int getItemCount() {
        return mListItems==null?0:mListItems.size();
    }
}
