package com.themelove.tlcostomview.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.base.adapter.TLViewHolder;

import java.util.List;

/**
 * Created by lqs on 2017/7/2.
 */

public class TMTextViewHolder extends TLViewHolder {
    private OnItemClickListener  mItemClickListener;
    private List<ListItem>       mListItems;
    private TextView             mTitle;
    private TextView             mSubTitle;

    public TMTextViewHolder(Context context, OnItemClickListener itemClickListener, List<ListItem> listItems){
        super(LayoutInflater.from(context).inflate(R.layout.item_tm_text, null, false));
        mItemClickListener=itemClickListener;
        mListItems=listItems;
        mTitle = (TextView) itemView.findViewById(R.id.item_tm_title);
        mSubTitle = (TextView) itemView.findViewById(R.id.item_tm_sub_title);
    }

    @Override
    public void setData() {
        if (mListItems!=null&&mListItems.size()>0){
            mTitle.setText(mListItems.get(getAdapterPosition()).getTitle());
            mSubTitle.setText(mListItems.get(getAdapterPosition()).getSubTitle());
        }
        itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener!=null){
                        mItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

