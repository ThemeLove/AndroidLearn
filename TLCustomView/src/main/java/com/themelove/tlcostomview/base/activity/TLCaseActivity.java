package com.themelove.tlcostomview.base.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.themelove.tlcostomview.R;

/**
 * Created by lqs on 2017/7/2.
 */

public abstract class TLCaseActivity extends AppCompatActivity {




    public void showMessageDialog(CharSequence title,CharSequence message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.blogOfGod:
                loadBlogOfGod();
                break;
            case R.id.blogOfMy:
                loadBlogOfMy();
                break;
            case R.id.normalApi:
                showNormalApi();
                break;
        }
        return true;
    }

    public abstract void loadBlogOfGod();

    public abstract void loadBlogOfMy();

    public abstract void showNormalApi();
}
