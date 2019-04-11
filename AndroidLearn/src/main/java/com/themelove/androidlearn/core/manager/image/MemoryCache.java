package com.themelove.androidlearn.core.manager.image;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

/**
 * author:qingshanliao
 * date:2019/4/11
 * 内存缓存
 */
public class MemoryCache implements ImageCache{
    private final LruCache<String, Bitmap> lruCache;
    public MemoryCache(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        lruCache = new LruCache<String, Bitmap>((int) (maxMemory/16)) {
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap bitmap) {
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public boolean saveBitmap(@NonNull String url,@NonNull Bitmap bitmap) {
        boolean isSaved=true;
        try {
            lruCache.put(url, bitmap);
        } catch (Exception e) {
            isSaved=false;
            e.printStackTrace();
        }
        return isSaved;
    }
}
