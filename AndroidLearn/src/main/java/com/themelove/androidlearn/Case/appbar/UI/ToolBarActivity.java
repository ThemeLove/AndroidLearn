package com.themelove.androidlearn.Case.appbar.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.themelove.androidlearn.R;

/**
 * Created by qingshanliao on 2017/2/7.
 */

/**
 * 该类是测试ToolBar的Activity
 */
public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//      因为我们在清单文件的背景中配置的Theme为Theme.AppCompat(深色的兼容主题)，默认是有ActionBar的，所以我们在代码中设置为不要ActionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView customView = (TextView) findViewById(R.id.customView);

//      下面是Toolbar的从左到右位置一次设置。

//      设置toolbar的导航栏图标
        toolbar.setNavigationIcon(R.mipmap.ic_drawer);
//      设置Logo
        toolbar.setLogo(R.mipmap.ic_add);

        toolbar.setTitle("Title");
//        toolbar.setTitleTextColor(Color.CYAN);
//      设置主标题的样式，可以包含字体颜色，大小等，
        toolbar.setTitleTextAppearance(this,R.style.ToolBarTitleStyle);
        toolbar.setSubtitle("SubTitle");
//        toolbar.setSubtitleTextColor(Color.RED);
//        设置副标题的样式，可以包含字体颜色，大小等
        toolbar.setSubtitleTextAppearance(this,R.style.ToolBarSubTitleStyle);
//      设置将toolbar代替actionbar，因为我们已经设置了supportRequestWindowFeature(Window.FEATURE_NO_TITLE);该类
        setSupportActionBar(toolbar);
//        toolbar.inflateMenu(R.menu.menu_actionbar);



//        点击事件
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolBarActivity.this,"自定义控件",Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolBarActivity.this,"导航图标",Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setLogoDescription("Logo");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.menu_item1:
                        Toast.makeText(ToolBarActivity.this,"分享",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_item1_subitem1:
                        Toast.makeText(ToolBarActivity.this,"子菜单1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_item2:
                        Toast.makeText(ToolBarActivity.this,"设置",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
