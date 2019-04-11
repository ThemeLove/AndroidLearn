package com.themelove.androidlearn.core.manager.image;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * author:qingshanliao
 * date:2019/4/11
 * 内存和硬盘缓存
 */
public class DoubleCache implements ImageCache{
    private MemoryCache memoryCache;
    private DiskCache diskCache;
    public DoubleCache(Context context){
        memoryCache =new MemoryCache();
        diskCache=new DiskCache(context);
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap;
        bitmap = memoryCache.getBitmap(url);
        if (bitmap!=null)return bitmap;
        bitmap = diskCache.getBitmap(url);
        if (bitmap!=null)return bitmap;
        return null;
    }

    /**
     * 缓存图片到MemoryCache 和DiskCache
     * @param url 图片url
     * @param bitmap 图片Bitmap
     * @return 一个成功return true,else return false
     */
    @Override
    public boolean saveBitmap(String url, Bitmap bitmap) {
        boolean isMemoryCacheSuccessed=memoryCache.saveBitmap(url,bitmap);
        boolean isDiskCacheSuccessed = diskCache.saveBitmap(url, bitmap);
        return isMemoryCacheSuccessed||isDiskCacheSuccessed;
    }
}
