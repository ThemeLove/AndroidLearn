package com.themelove.androidlearn.demo.createshortcut;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.themelove.androidlearn.R;
import com.themelove.androidlearn.base.TLActivity;
import com.themelove.androidlearn.utils.DisplayUtil;
import com.themelove.androidlearn.utils.TipUtil;

/**
 * 通过广播动态创建和移除桌面快捷方式
 * 原理：创建和和删除桌面快捷方式都是通过发送广播实现的，系统桌面应用程序launcher通过监听广播传递过来的intent,来判断是创建快捷方式还是删除
 * 注意：
 * 1、由于手机厂商基本上都会在Android原生系统的launcher基础上定制，所以存在很多兼容性问题，比如在魅族5手机上下面的demo就卸载不了快捷方式，vivo手机上就可以
 * 2、因为桌面launcher会将桌面快捷图标存在数据库中，所以可以用contentProvider来直接修改数据库，这样兼容性更好，且效率更高，还可以动态的改变桌面快捷方式。
 *
 * author:qingshanliao
 * date:2018/1/26
 */
public class DynamicCreateShortCutActivity extends TLActivity {
    private String icon_name="我是动态创建的桌面图标";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("data")){
            String data=getIntent().getExtras().getString("data");
            TipUtil.showToast(data);
        }

        DisplayUtil.initScreen(this);
        FrameLayout container = new FrameLayout(this);
        container.setBackgroundColor(Color.GRAY);

        TextView createShortCut = new TextView(this);
        FrameLayout.LayoutParams createParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,DisplayUtil.dip2px(50));
        createParams.topMargin=50;
        createParams.gravity= Gravity.LEFT;
        createShortCut.setText("动态创建桌面图标");
        createShortCut.setGravity(Gravity.CENTER);
        createShortCut.setPadding(10,10,10,10);
        createShortCut.setBackgroundColor(Color.CYAN);
        container.addView(createShortCut,createParams);

        FrameLayout.LayoutParams deleteParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,DisplayUtil.dip2px(50));
        deleteParams.topMargin=50;
        deleteParams.gravity=Gravity.RIGHT;
        final TextView deleteShortCut=new TextView(this);
        deleteShortCut.setText("删除桌面快捷图标");
        deleteShortCut.setBackgroundColor(Color.YELLOW);
        deleteShortCut.setGravity(Gravity.CENTER);
        deleteShortCut.setPadding(10,10,10,10);
        container.addView(deleteShortCut,deleteParams);


        setContentView(container);

        createShortCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicCreateShortCut();
                TipUtil.showToast("dynamicCreateShortCut");
            }
        });

        deleteShortCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShortCut();
                TipUtil.showToast("deleteShortCut");
            }
        });

    }

    /**
     * 动态创建桌面快捷图标
     */
    private void dynamicCreateShortCut(){
        Intent shortCutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortCutIntent.putExtra("duplicate",false);//不允许重复创建
//        指定title
        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,icon_name);
//        指定图标 方式1
//        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, R.mipmap.ic_add);
//        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, R.mipmap.ic_add);
//        指定图标 方式2
        Intent.ShortcutIconResource shortcutIconResource = Intent.ShortcutIconResource.fromContext(DynamicCreateShortCutActivity.this, R.mipmap.ic_add);
        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,shortcutIconResource);

//        指定点击图标时跳转的意图intent,设置flag、可以传递data
        Intent actionIntent=new Intent( );

//        ClEAER_TOP销毁目标Activity和它之上的所有Activity，重新创建目标Activity
        actionIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        actionIntent.setClassName(this.getPackageName(),this.getClass().getName());
//        传递数据
        actionIntent.putExtra("data","{borderId:11234,bordernName:fengtimo,borderGender:female}");
        shortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,actionIntent);

        DynamicCreateShortCutActivity.this.sendBroadcast(shortCutIntent);
    }

    /**
     * 删除桌面快捷图标
     */
    private void deleteShortCut(){
        Intent deleteShortCut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        deleteShortCut.putExtra("duplicate",false);//不允许重复创建
        deleteShortCut.putExtra(Intent.EXTRA_SHORTCUT_NAME,icon_name);

        Intent.ShortcutIconResource shortcutIconResource = Intent.ShortcutIconResource.fromContext(DynamicCreateShortCutActivity.this, R.mipmap.ic_add);
        deleteShortCut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,shortcutIconResource);

        Intent actionIntent=new Intent();
        actionIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        actionIntent.setClassName(this.getPackageName(),this.getClass().getName());
        actionIntent.putExtra("data","{borderId:11234,bordernName:fengtimo,borderGender:female}");
        deleteShortCut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,actionIntent);

        DynamicCreateShortCutActivity.this.sendBroadcast(deleteShortCut);

    }
}
