package com.themelove.androidlearn.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.themelove.androidlearn.R;

/**
 * Created by qingshanliao on 2017/2/6.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//      设置ActionBar不可用
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_logo);
        //设置主标题字体
        toolbar.setTitle("我是主标题");
        //设置主标题样式
        toolbar.setTitleTextAppearance(MainActivity.this,R.style.ToolBar_Title_Text);
        //设置副标题
        toolbar.setSubtitle("我是副标题");
        //设置副标题样式
        toolbar.setSubtitleTextAppearance(MainActivity.this,R.style.ToolBar_SubTitle_Text);
        //用toolbar代替ActionBar
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();


        //设置导航图标
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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

        //导航栏的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
