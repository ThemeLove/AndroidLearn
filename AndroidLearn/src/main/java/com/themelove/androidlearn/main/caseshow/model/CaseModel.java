package com.themelove.androidlearn.main.caseshow.model;

/**
 * Created by qingshanliao on 2017/2/20.
 */


import com.themelove.androidlearn.R;
import com.themelove.androidlearn.main.caseshow.bean.CaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据层，用于为CaseFragment提供数据的model
 */
public class CaseModel{
    /**case标题*/
    private static String[]  mCaseTitles;

    /**case标题的描述*/
    private static String[]  mCaseDescs;
    /**case跳转的Action的类全名*/
    private static String[]  mCaseActions;
    /**case对应的图片*/
    private static int   []  mCaseDrawables;

    static{
        mCaseTitles=new String[]{
                "Dagger2",
                "ToolBar的使用",
                "PagerTabStrip最佳实战",
                "CustomProgressBar",
                "MediaPlayer",
                "ShortCut",
                "外链跳转到app页面",
                "事件传递机制"
        };

        mCaseDescs=new String[]{
                "（Dagger2用法测试）",
                "（ToolBar的详细使用）",
                "（PagerTabStrip和ViewPager的结合使用）",
                "（自定义ProgressBar下载进度条）",
                "（ListView中播放音频和帧动画结合使用）",
                "动态创建、删除图标桌面图标",
                "外链跳转到app内指定页面",
                "事件传递机制测试"
        };

        mCaseActions=new String[]{
                "com.themelove.androidlearn.demo.dagger2.Dagger2Activity",
                "com.themelove.androidlearn.demo.appbar.ui.ToolBarActivity",
                "com.themelove.androidlearn.demo.PagerTab.PagerTabStripActivity",
                "com.themelove.androidlearn.demo.progressbar.ProgressBarTestActivity",
                "com.themelove.androidlearn.demo.media.AudioActivity",
                "com.themelove.androidlearn.demo.createshortcut.DynamicCreateShortCutActivity",
                "com.themelove.androidlearn.demo.linkjump2apppage.LinkJump2AppPageActivity",
                "com.themelove.androidlearn.demo.touchevent.TouchEventActivity",
        };
    }

    private static CaseModel instance=new CaseModel();

    private CaseModel(){}

    public static CaseModel getInstance(){
        return instance;
    }

    public  List<CaseBean> getCaseList(){
        List<CaseBean> caseList = new ArrayList<>();
        for (int i=0;i<mCaseTitles.length;i++){
            CaseBean caseBean = new CaseBean(mCaseTitles[i], mCaseDescs[i],R.drawable.bg_item_case,mCaseActions[i]);
            caseList.add(caseBean);
        }
        return caseList;
    }

}
