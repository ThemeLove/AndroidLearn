package com.themelove.androidlearn.main.caseshow.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.themelove.androidlearn.base.TLFragment;
import com.themelove.androidlearn.R;
import com.themelove.androidlearn.main.caseshow.bean.CaseBean;
import com.themelove.androidlearn.main.caseshow.model.CaseModel;

import java.util.ArrayList;

/**
 * Created by qingshanliao on 2017/2/16.
 */

public class CaseFragment extends TLFragment {

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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
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
        caseList = (ArrayList<CaseBean>) CaseModel.getInstance().getCaseList();

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
        caseAdapter.setOnItemClickListener(new CaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(CaseBean bean) {
                try {
                    Log.i("TL","onItemClick");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("TLread:out",Thread.currentThread().getName());
                            String s=null;
//                            s.toString();
                        }
                    }).start();
                    //      全局异常测试

                    Intent intent = new Intent(getActivity(), Class.forName(bean.getToClass()));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        });
        mRecyclerView.setAdapter(caseAdapter);
    }

    @Override
    public void onResume() {
//        setState(State.LOADING);
//        refreshData();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","caseFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView","caseFragment");
    }
}
