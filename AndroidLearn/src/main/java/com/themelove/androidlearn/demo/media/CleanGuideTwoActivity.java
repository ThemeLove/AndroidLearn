package com.themelove.androidlearn.demo.media;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;


import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.TLActivity;
import com.themelove.androidlearn.utils.TipUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author:qingshanliao
 * date:2019/4/8
 */
public class CleanGuideTwoActivity extends TLActivity implements View.OnClickListener {
    private final String TAG=CleanGuideTwoActivity.class.getSimpleName();
    String mImageUrl;
    String mApkTarget;
    String mImageId;
    private ViewPager mViewPager;
    private boolean isCheckPPtvDownload = true;
    private boolean mIsExistPPtvImage;
    private LinearLayout mPagerIndicatorLl;
    private ImageView mPpIvCheck;
    private boolean isDragging=false;
    private int mCurrentPosition =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_new_guide);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerIndicatorLl = (LinearLayout) findViewById(R.id.loPageTurningPoint);

        initData();
        initListener();
    }


    public class GuideBean {
        /**
         * viewPage显示的View
         */
        View pageView;

        /**
         * videoView
         */
        VideoView videoView;
        /**
         * indicator View
         */
        ImageView indicatorView;

        /**
         * 是否是pp视频下载，0:不是，1是
         */
        int type = 0;
    }

    private List<GuideBean> mGuideBeanList = new ArrayList<>();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        updatePageStatus();
    }

    private int[] mPagerVideoArray=new int[]{R.raw.guide_video1,R.raw.guide_video2,R.raw.guide_video3};
    private int[] mPageIndicatorArray = new int[]{R.drawable.ic_dot_gray_guide, R.drawable.iv_dot_red_guide};
    private int[] mPageBgArray = new int[]{R.drawable.guide_bg1,R.drawable.guide_bg2,R.drawable.guide_bg3};

    protected void initData() {
        mApkTarget = "http://wwww.baidu.com";
        mImageUrl = "http://wwww.baidu.com";
        mImageId = "123";
        mIsExistPPtvImage = !TextUtils.isEmpty(mImageUrl);

        for (int i = 0; i < 3; i++) {
            View commonView = LayoutInflater.from(this).inflate(R.layout.activity_guide_3, null);
            final int temp =i;
            final VideoView commonVideo = (VideoView) commonView.findViewById(R.id.video_bg);
            ImageView commonIvStart = (ImageView) commonView.findViewById(R.id.iv_start);
            commonVideo.setBackgroundResource(mPageBgArray[i]);

            commonIvStart.setVisibility((i == 2 && !mIsExistPPtvImage) ? View.VISIBLE : View.INVISIBLE);
            commonIvStart.setOnClickListener(this);

            commonVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + mPagerVideoArray[i]));
            commonVideo.start();


            GuideBean guideBean = new GuideBean();
            guideBean.type = 0;
            guideBean.pageView = commonView;
            guideBean.videoView = commonVideo;

            ImageView indicatorView = new ImageView(this);
            indicatorView.setPadding(7, 0, 7, 0);
            indicatorView.setImageResource(i==0?mPageIndicatorArray[1]:mPageIndicatorArray[0]); //默认灰色
            guideBean.indicatorView = indicatorView;
            mPagerIndicatorLl.addView(indicatorView);
            mGuideBeanList.add(guideBean);
        }
        if (mIsExistPPtvImage) {

            View ppView = LayoutInflater.from(this).inflate(R.layout.view_pptv_download, null);
            ImageView ppIvBg = (ImageView) ppView.findViewById(R.id.iv_bg);
            mPpIvCheck = (ImageView) ppView.findViewById(R.id.iv_check);
            ppView.findViewById(R.id.iv_start).setOnClickListener(this);
//            setPPImage(ppIvBg);
//            isCheckPPtvDownload = RxNetUtil.getCurrentNetStatus(this).isWifi();
            mPpIvCheck.setImageResource(isCheckPPtvDownload ? R.drawable.ic_pptv_selected : R.drawable.ic_pptv_unselected);

            mPpIvCheck.setOnClickListener(this);

            GuideBean guideBean = new GuideBean();
            guideBean.type = 1;
            guideBean.pageView = ppView;

            ImageView indicatorView = new ImageView(this);
            indicatorView.setPadding(7, 0, 7, 0);
            indicatorView.setImageResource(mPageIndicatorArray[0]); //默认灰色

            guideBean.indicatorView = indicatorView;
            mPagerIndicatorLl.addView(indicatorView);
            mGuideBeanList.add(guideBean);
        }

        List<View> pageViewList = new ArrayList<>();
        if (mGuideBeanList != null && mGuideBeanList.size() > 0) {
            for (GuideBean guideBean : mGuideBeanList) {
                pageViewList.add(guideBean.pageView);
            }
        }
        mViewPager.setOffscreenPageLimit(mGuideBeanList.size()-1);
        mViewPager.setAdapter(new GuidePagerAdapter(pageViewList));
        mViewPager.setCurrentItem(0);
//        updatePageStatus();
    }

    protected void initListener() {

        for (int i=0;i<mGuideBeanList.size();i++){
            int temp=i;
            GuideBean guideBean=mGuideBeanList.get(i);
            if (guideBean.type==0&&guideBean.videoView!=null){
                guideBean.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                            @Override
                            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                                if (what==MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){
                                    Log.i(TAG,"onInfo----->i="+temp);
                                    guideBean.videoView.setBackgroundColor(Color.TRANSPARENT);
                                    return true;
                                }
                                return false;
                            }
                        });

                    }
                });

                guideBean.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Log.i(TAG,"onCompletion----->i="+temp);
//                        guideBean.videoView.start();
                    }
                });

            }

        }


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_DRAGGING){ //滑动状态
                    isDragging = true;
                    updatePageStatus();
                }else if(state==ViewPager.SCROLL_STATE_IDLE){//闲置状态
                    isDragging=false;
                    updatePageStatus();
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,"onPageSelected,position=" + position);
                mCurrentPosition = position;
                updatePageStatus();
            }
        });
    }

    private void updatePageStatus() {
        mPagerIndicatorLl.setVisibility((mCurrentPosition==mGuideBeanList.size()-1)?View.GONE:View.VISIBLE);

        for (int i=0;i<mGuideBeanList.size();i++){
            GuideBean guideBean = mGuideBeanList.get(i);
            if (guideBean.type==0){
                guideBean.videoView.setBackgroundResource((i==mCurrentPosition)?android.R.color.transparent:mPageBgArray[i]);
            }
            guideBean.indicatorView.setImageResource(i==mCurrentPosition?mPageIndicatorArray[1]:mPageIndicatorArray[0]);
        }
        GuideBean guideBean = mGuideBeanList.get(mCurrentPosition);
        if (guideBean.type==0){
            if (isDragging){
                guideBean.videoView.pause();
            }else{
                guideBean.videoView.start();
            }
        }




/*        for (int i = 0; i < mGuideBeanList.size(); i++) {
            GuideBean guideBean = mGuideBeanList.get(i);
            int type=guideBean.type;
            VideoView videoView=guideBean.videoView;
            if (type==0){
                if(videoView!=null){
                    if (i==mCurrentPosition){
                        videoView.pause();
                        if (!isDragging){
                            videoView.start();
                        }
                    }else{
                        videoView.pause();
                        videoView.setBackgroundResource(mPageBgArray[i]);
                    }
                }
            }
            guideBean.indicatorView.setImageResource(i==mCurrentPosition?mPageIndicatorArray[1]:mPageIndicatorArray[0]);
        }*/
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_check) {
            isCheckPPtvDownload = !isCheckPPtvDownload;
            mPpIvCheck.setImageResource(isCheckPPtvDownload ? R.drawable.ic_pptv_selected : R.drawable.ic_pptv_unselected);
        } else if (v.getId() == R.id.iv_start) {
            if (mIsExistPPtvImage && !TextUtils.isEmpty(mApkTarget) && isCheckPPtvDownload) {
//                DownLoadUtil.pptv(mApkTarget);
//                LR.reportLandingAdvertClick();
                TipUtil.showToast("正在后台下载中...");
            }
//            this.finish();
        }
    }
}
