package com.themelove.androidlearn.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.themelove.androidlearn.R;

import java.util.List;

/**
 * Created by qingshanliao on 2017/2/17.
 */

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.CaseViewHolder>{
    private List<CaseBean> caseList;

    public CaseAdapter(List<CaseBean> caseList){
        this.caseList=caseList;
    }


    @Override
    public CaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_case, parent, false);
        return new CaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaseViewHolder holder, int position) {
        holder.textView.setText(caseList.get(position).getTitle());
        holder.textView.setBackgroundResource(caseList.get(position).getResId());
    }

    @Override
    public int getItemCount() {
        return caseList.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder{

        private  TextView textView;

        public CaseViewHolder(View itemView) {
            super(itemView);
             textView =  (TextView) itemView.findViewById(R.id.item_case);
        }
    }
}
