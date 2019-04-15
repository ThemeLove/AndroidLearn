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

/**
 * author:lqs
 * date:2019/4/8
 */
public class CleanNewGuideActivity extends TLActivity implements View.OnClickListener {
    private final String TAG=CleanNewGuideActivity.class.getSimpleName();

    String mImageUrl;
    String mApkTarget;
    String mImageId;
    private boolean mIsExistPPtvImage;

    private boolean mIsCheckPPtvDownload = true;
    private ViewPager mViewPager;
    private LinearLayout mPageIndicatorLl;
    private ImageView mPpIvCheck;
    private boolean isDragging=false;
    private int mCurrentPosition =0;

    private int[] mPageVideoArray =new int[]{R.raw.new_guide_video1,R.raw.new_guide_video2,R.raw.new_guide_video3};
    private int[] mPageIndicatorArray = new int[]{R.drawable.ic_dot_gray_guide, R.drawable.iv_dot_red_guide};
    private int[] mPageBgArray = new int[]{R.drawable.new_guide_bg1,R.drawable.new_guide_bg2,R.drawable.new_guide_bg3};
    private ArrayList<View> mPageViewList=new ArrayList<>();
    private ArrayList<VideoView> mVideoViewList=new ArrayList<>();
    private ArrayList<ImageView> mIndicatorViewList=new ArrayList<>();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)updatePageStatus();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_new_guide);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPageIndicatorLl = (LinearLayout) findViewById(R.id.pageIndicatorLl);
        initData(savedInstanceState);
        initListener();
    }

    protected void initData(Bundle savedInstanceState) {
        mApkTarget = getIntent().getStringExtra("apkTarget");
        mImageUrl = getIntent().getStringExtra("imageUrl");
        mImageId = getIntent().getStringExtra("imageId");
        mIsExistPPtvImage = !TextUtils.isEmpty(mImageUrl);

        for (int i = 0; i < mPageVideoArray.length; i++) {
            View commonView = LayoutInflater.from(this).inflate(R.layout.view_new_guide_video, mViewPager,false);
            final VideoView videoView = (VideoView) commonView.findViewById(R.id.video_bg);
            ImageView ivStart = (ImageView) commonView.findViewById(R.id.iv_start);
            ivStart.setVisibility((i == mPageVideoArray.length-1 && !mIsExistPPtvImage) ? View.VISIBLE : View.INVISIBLE);
            ivStart.setOnClickListener(this);
            mPageViewList.add(commonView);
            mVideoViewList.add(videoView);

//            videoView.setBackgroundResource(mPageBgArray[i]);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + mPageVideoArray[i]));

            ImageView indicatorView = new ImageView(this);
            indicatorView.setPadding(7, 0, 7, 0);
            indicatorView.setImageResource(mPageIndicatorArray[0]); //默认灰色
            mIndicatorViewList.add(indicatorView);
            mPageIndicatorLl.addView(indicatorView);
        }

        if (mIsExistPPtvImage) {
            View ppView = LayoutInflater.from(this).inflate(R.layout.view_new_guide_pptv_download,mViewPager,false);
            ImageView ppIvBg = (ImageView) ppView.findViewById(R.id.iv_bg);
            mPageViewList.add(ppView);

            mPpIvCheck = (ImageView) ppView.findViewById(R.id.iv_check);
            ppView.findViewById(R.id.iv_start).setOnClickListener(this);
            setPPImage(ppIvBg);

            mPpIvCheck.setImageResource(mIsCheckPPtvDownload ? R.drawable.ic_pptv_selected : R.drawable.ic_pptv_unselected);
            mPpIvCheck.setOnClickListener(this);

            ImageView indicatorView = new ImageView(this);
            indicatorView.setPadding(7, 0, 7, 0);
            indicatorView.setImageResource(mPageIndicatorArray[0]); //默认灰色
            mIndicatorViewList.add(indicatorView);
            mPageIndicatorLl.addView(indicatorView);
        }
        mViewPager.setOffscreenPageLimit(mPageViewList.size()-1);
        mViewPager.setAdapter(new GuidePagerAdapter(mPageViewList));
        mViewPager.setCurrentItem(0);
    }

    protected void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG,"onPageScrollStateChanged,state=" + state);
                if (state== ViewPager.SCROLL_STATE_DRAGGING){ //滑动状态
                    isDragging = true;
                    updatePageStatus();
                }else if(state== ViewPager.SCROLL_STATE_IDLE){//闲置状态
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
        for (VideoView videoView : mVideoViewList) {
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            Log.i(TAG,"onInfo");
                            if (what== MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){
                                videoView.setBackgroundColor(Color.TRANSPARENT);
                                return true;
                            }
                            return false;
                        }
                    });
                }
            });

            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener(){
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
//                  错误监听，播放异常的时候，则停止播放，防止弹窗阻塞UI
                    videoView.stopPlayback();
                    return true;
                }
            });
        }
    }

    private void updatePageStatus() {
        mPageIndicatorLl.setVisibility((mCurrentPosition==mPageViewList.size()-1)? View.GONE: View.VISIBLE);
        for (int i=0;i<mPageViewList.size();i++){
            ImageView indicatorView = mIndicatorViewList.get(i);
            indicatorView.setImageResource(i==mCurrentPosition?mPageIndicatorArray[1]:mPageIndicatorArray[0]);
            if (i<mVideoViewList.size()){
                VideoView videoView = mVideoViewList.get(i);
                if (videoView!=null){
//                    videoView.setBackgroundResource(mPageBgArray[i]);
                    if (i==mCurrentPosition){
                        if (isDragging){
//                            videoView.pause();
                            videoView.stopPlayback();
                        }else{
                            videoView.resume();
                            videoView.start();
//                            videoView.setBackgroundResource(mPageBgArray[i]);
                        }
                    }
                }
            }
            if ((mCurrentPosition==mPageViewList.size()-1)&&!TextUtils.isEmpty(mImageUrl)){
            }
        }

    }

    private void setPPImage(final ImageView ppImage) {
/*        FrescoUtil.loadImageBitmap(mImageUrl, new ResizeOptions(mScreenUtil.getAppWidth(), mScreenUtil.getAppHeight()), new FrescoUtil.BitmapLoadListener(BitmapHelper.CACHE_KEY_GLOBAL, mImageUrl) {
            @Override
            protected void onSafeResultImpl(Bitmap bitmap) {
                ppImage.setImageBitmap(bitmap);
            }
            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_check) {
            mIsCheckPPtvDownload = !mIsCheckPPtvDownload;
            mPpIvCheck.setImageResource(mIsCheckPPtvDownload ? R.drawable.ic_pptv_selected : R.drawable.ic_pptv_unselected);
        } else if (v.getId() == R.id.iv_start) {
            if (mIsExistPPtvImage && !TextUtils.isEmpty(mApkTarget) && mIsCheckPPtvDownload) {
//                DownLoadUtil.pptv(mApkTarget);
//                LR.reportLandingAdvertClick();
                TipUtil.showToast("正在后台下载中...");
            }
//            QtMainActivity.getInstance().start(this);
            this.finish();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy..");
        for (VideoView videoView:mVideoViewList){
            if (videoView!=null){
                videoView.stopPlayback();
                videoView.setOnPreparedListener(null);
                videoView=null;
            }
        }
    }
}