package com.themelove.androidlearn.main;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.themelove.androidlearn.AppBase.BaseFragment;
import com.themelove.androidlearn.R;

import java.util.ArrayList;

/**
 * Created by qingshanliao on 2017/2/16.
 */

public class CaseFragment extends BaseFragment {

    private ArrayList<CaseBean> caseList;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_case, null, false);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recylerView);
        mSwipeLayout.setColorSchemeColors(Color.BLUE,Color.CYAN,Color.RED);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(5);
        mRecyclerView.addItemDecoration(spaceItemDecoration);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void initData(final LoadingListener listener) {

        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mSwipeLayout.setRefreshing(false);
                listener.onLoadResult();
            }
        };
        caseList = new ArrayList<CaseBean>();
        for (int i=0;i<30;i++){
            CaseBean caseBean = new CaseBean("PagerTabStrip最佳实战" + i, R.drawable.bg_item_case);
            caseList.add(caseBean);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        },2000);
    }

    @Override
    public void setData() {
        setState(State.SUCCESS);
        CaseAdapter caseAdapter = new CaseAdapter(caseList);
        mRecyclerView.setAdapter(caseAdapter);

    }
}
