package com.themelove.androidlearn.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.themelove.androidlearn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingshanliao on 2017/2/6.
 */
public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;
    private List<MainTitle> mMainTitles;

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ViewPager mPager;
    private PagerSlidingTab mPagerTab;
    private LinearLayout mLeftMenu;
    private TextView mMenuSettings;
    private TextView mMenuDec;
    private ImageView mIvSearch;
    private ImageView mSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      设置ActionBar不可用
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//      设置全屏
//      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initData();
        initView();
//      initViewTest();
        initAction();
    }

    private void initData() {
        mFragments=new ArrayList();
        for (int i=1;i<10;i++){
            HomeFragment homeFragment = new HomeFragment();
            mFragments.add(homeFragment);
        }
//        HomeFragment homeFragment = new HomeFragment();
//        CaseFragment caseFragment = new CaseFragment();
//
//        mFragments.add(homeFragment);
//        mFragments.add(caseFragment);

        mMainTitles =new ArrayList();
        for (int i=1;i<10;i++){
            MainTitle mainTitle = new MainTitle("第" + i + "页", R.mipmap.ic_share, i, i);
            mMainTitles.add(mainTitle);
        }
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSearch = (ImageView) findViewById(R.id.iv_search);
        mDrawer=  (DrawerLayout) findViewById(R.id.drawer);
        mPager = (ViewPager) findViewById(R.id.vp_main);
        mPagerTab = (PagerSlidingTab) findViewById(R.id.pagerTab);
        mLeftMenu = (LinearLayout) findViewById(R.id.ll_leftMenu);
        mMenuSettings = (TextView) findViewById(R.id.tv_menu_settings);
        mMenuDec = (TextView) findViewById(R.id.tv_menu_dec);
    }

    private void initAction() {
        //设置主标题
        mToolbar.setTitle("ThemeLove");
        //设置主标题样式
        mToolbar.setTitleTextAppearance(MainActivity.this,R.style.ToolBar_Title_Text);
        //设置副标题
        mToolbar.setSubtitle("ThemeLove");
        //设置副标题样式
        mToolbar.setSubtitleTextAppearance(MainActivity.this,R.style.ToolBar_SubTitle_Text);
        //用toolbar代替ActionBar,该句话的意思是，当你调用了setSupportActionBar()时，就是用toolBar替换了ActionBar，那么所有的
    //        原始回调都会走ActionBar的，所以如果你不重写onCreateOptionsMenu方法的话，Menu就不会出现。
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//      Drawer相关
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        mDrawer.setDrawerListener(drawerToggle);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId==R.id.menu_settings){
                    Toast.makeText(MainActivity.this,"你点击了设置菜单",Toast.LENGTH_SHORT).show();
                }else if(itemId==R.id.menu_share){
                    Toast.makeText(MainActivity.this,"你点击了分享菜单",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

//      设置ViewPager的Adapter
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments, mMainTitles);
        mPager.setAdapter(mainPagerAdapter);
        mPagerTab.setViewPager(mPager);
}


    private void initViewTest() {
        //        设置导航图标
        //        mToolbar.setNavigationIcon(R.mipmap.ic_drawer);
        //        mToolbar.inflateMenu(R.menu.menu_main);
    }
}
