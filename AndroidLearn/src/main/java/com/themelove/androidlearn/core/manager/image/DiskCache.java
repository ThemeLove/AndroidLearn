package com.themelove.androidlearn.core.manager.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.themelove.androidlearn.utils.MD5Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * author:qingshanliao
 * date:2019/4/11
 */
public class DiskCache implements ImageCache{
    private final String TAG = DiskCache.class.getSimpleName();
    private String diskCacheDirName="DiskCache";
    private File diskCacheDir;
    public DiskCache(Context context){
        diskCacheDir = context.getExternalFilesDir(diskCacheDirName);
    }

    /**
     * 从文件缓存中获取图片
     * @param url 图片url
     * @return bitmap or null
     */
    @Override
    public Bitmap getBitmap(@NonNull String url) {
        String imageCachePath=diskCacheDir.getAbsolutePath()+File.separator+MD5Util.MD5EncryptString(url);
        File imageCacheFile = new File(imageCachePath);
        if (imageCacheFile.exists()){
            try {
                FileInputStream fis = new FileInputStream(imageCacheFile);
                return BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将图片保存到文件缓存
     * @param url 文件url
     * @param bitmap bitmap
     * @return true or false
     */
    @Override
    public boolean saveBitmap(@NonNull String url, @NonNull Bitmap bitmap) {
        boolean isSaveSuccessed=false;
        try {
            FileOutputStream fos = new FileOutputStream(diskCacheDir.getAbsoluteFile() + "/" + MD5Util.MD5EncryptString(url));
            isSaveSuccessed = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return isSaveSuccessed;
    }

}
