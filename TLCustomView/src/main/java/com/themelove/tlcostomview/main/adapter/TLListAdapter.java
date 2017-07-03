package com.themelove.tlcostomview.main.adapter;

import android.view.ViewGroup;

import com.themelove.tlcostomview.base.adapter.TLBaseAdapter;

import java.util.List;

/**
 * Created by lqs on 2017/7/2.
 */

public class TLListAdapter extends TLBaseAdapter<TMTextViewHolder> {

    private List<ListItem>       mListItems;
    private OnItemClickListener  mItemClickListener;
    public TLListAdapter(List<ListItem> listItems, OnItemClickListener itemClickListener){
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
