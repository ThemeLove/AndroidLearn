package com.themelove.tlcostomview.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.themelove.tlcostomview.Case.draw.drawfour.DrawFourTestActivity;
import com.themelove.tlcostomview.Case.draw.drawone.DrawOneTestActivity;
import com.themelove.tlcostomview.Case.draw.drawthree.DrawThreeTestActivity;
import com.themelove.tlcostomview.Case.draw.drawtwo.DrawTwoTestActivity;
import com.themelove.tlcostomview.R;
import com.themelove.tlcostomview.main.bean.ListItem;
import com.themelove.tlcostomview.main.adapter.ListRecyclerCardItemDecoration;
import com.themelove.tlcostomview.main.adapter.OnItemClickListener;
import com.themelove.tlcostomview.main.adapter.TLListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lqs on 2017/6/9.
 */

public class CaseFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private String[] mDrawCasesTitles;
    private String[] mDrawCasesDescs;
    private List<ListItem> mListItems;

    public static CaseFragment getInstance(int position){
        Log.i("CaseFragment","position："+position);
        CaseFragment caseFragment = new CaseFragment();
        Bundle argument= caseFragment.getArguments();
        if(argument==null)argument=new Bundle();
        argument.putInt("position",position);
        caseFragment.setArguments(argument);
        return caseFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawCasesTitles = getResources().getStringArray(R.array.draw_cases_titles);
        mDrawCasesDescs = getResources().getStringArray(R.array.draw_cases_descs);
        mListItems = new ArrayList<ListItem>();
        for (int i=0;i<mDrawCasesTitles.length;i++){
            ListItem listItem = new ListItem(mDrawCasesTitles[i], mDrawCasesDescs[i]);
            mListItems.add(listItem);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_custom,container,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.addItemDecoration(new ListRecyclerCardItemDecoration());
        mRecyclerView.setLayoutManager(linearLayoutManager);
 /*     int position =getArguments().getInt("position");
        Log.i("onCreateView","position："+position);
        TextView textView = new TextView(getActivity());
        textView.setText( position+"");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(100);
        textView.setTextColor(Color.RED);
        return textView;*/
        return mRecyclerView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TLListAdapter tmListAdapter = new TLListAdapter(mListItems, mItemClickListener);
        mRecyclerView.setAdapter(tmListAdapter);
    }

    private OnItemClickListener mItemClickListener=new OnItemClickListener(){

        @Override
        public void onItemClick(int position) {
            Intent intent = new Intent();
            switch (position){
                case 0:
                    intent.setClass(getActivity(), DrawOneTestActivity.class);
                    break;
                case 1:
                    intent.setClass(getActivity(), DrawTwoTestActivity.class);
                    break;
                case 2:
                    intent.setClass(getActivity(), DrawThreeTestActivity.class);
                    break;
                case 3:
                    intent.setClass(getActivity(),DrawFourTestActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    };

}
