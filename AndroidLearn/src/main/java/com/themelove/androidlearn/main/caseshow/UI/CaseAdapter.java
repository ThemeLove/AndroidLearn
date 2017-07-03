package com.themelove.androidlearn.main.caseshow.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.main.caseshow.bean.CaseBean;

import java.util.List;

/**
 * Created by qingshanliao on 2017/2/17.
 */

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.CaseViewHolder>{
    private List<CaseBean> caseList;

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            mOnItemClickListener=onItemClickListener;
    }

    /**
     * CaseAdapter的Item点击监听
     */
    public interface OnItemClickListener{
        void onItemClickListener(CaseBean bean);
    }

    public CaseAdapter(List<CaseBean> caseList){
        this.caseList=caseList;
    }

    @Override
    public CaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_case, parent, false);
        return new CaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaseViewHolder holder, final int position) {
        holder.itemView.setBackgroundResource(caseList.get(position).getResId());
        holder.mCaseTitle.setText(caseList.get(position).getTitle());
        holder.mCaseDesc.setText(caseList.get(position).getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClickListener(caseList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return caseList.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder{

        private  TextView mCaseTitle;
        private  TextView mCaseDesc;

        public CaseViewHolder(View itemView) {
            super(itemView);
            mCaseTitle =  (TextView) itemView.findViewById(R.id.case_title);
            mCaseDesc  =  (TextView) itemView.findViewById(R.id.case_desc);
        }
    }
}
