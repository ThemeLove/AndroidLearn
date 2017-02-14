package com.themelove.androidlearn.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.themelove.androidlearn.R;

/**
 * Created by qingshanliao on 2017/2/6.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      设置ActionBar不可用
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//      设置全屏
//      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
         toolbar = (Toolbar) findViewById(R.id.toolbar);

        //设置主标题字体
        toolbar.setTitle("我是主标题");
        //设置主标题样式
        toolbar.setTitleTextAppearance(MainActivity.this,R.style.ToolBar_Title_Text);
        //设置副标题
        toolbar.setSubtitle("我是副标题");
        //设置副标题样式
        toolbar.setSubtitleTextAppearance(MainActivity.this,R.style.ToolBar_SubTitle_Text);
        //用toolbar代替ActionBar,该句话的意思是，当你调用了setSupportActionBar()时，就是用toolBar替换了ActionBar，那么所有的
//        原始回调都会走ActionBar的，所以如果你不重写onCreateOptionsMenu方法的话，Menu就不会出现。
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置导航图标
        toolbar.setNavigationIcon(R.mipmap.ic_drawer);
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
                Toast.makeText(MainActivity.this,"你点击了导航图标",Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationContentDescription("我是导航图标");

        findViewById(R.id.iv_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"我是ToolBar的自定义控件",Toast.LENGTH_SHORT).show();
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.menu_main,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int itemId = item.getItemId();
//        switch (itemId){
//            case R.id.menu_settings:
//                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_share:
//                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    protected void onResume() {
        super.onResume();
        /*toolbar.setLogo(R.mipmap.ic_logo);
        //设置主标题字体
        toolbar.setTitle("我是主标题");
        //设置主标题样式
        toolbar.setTitleTextAppearance(MainActivity.this,R.style.ToolBar_Title_Text);
        //设置副标题
        toolbar.setSubtitle("我是副标题");
        //设置副标题样式
        toolbar.setSubtitleTextAppearance(MainActivity.this,R.style.ToolBar_SubTitle_Text);*/
    }
}
