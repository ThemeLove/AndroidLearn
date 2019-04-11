package com.themelove.androidlearn.core.manager.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 该类是自己实现的图片加载器
 * 支持图片的三级缓存，内存、文件和网络
 * author:qingshanliao
 * date:2019/4/11
 */
public class ImageLoader {
    private final String TAG =ImageLoader.class.getSimpleName();
    private  ImageCache imageCache;
    private static ImageLoader instance;

    public static ImageLoader getInstance(){
        if (instance==null){
            synchronized (ImageLoader.class) {
                if (instance==null){
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    private ImageLoader(){}

    public  void setImageCache(ImageCache imageCache){
        this.imageCache=imageCache;
    }

    public ImageCache getImageCache(){
        return imageCache;
    }

    public boolean displayImage(String url, ImageView imageView){
        Bitmap bitmap;
        bitmap = imageCache.getBitmap(url);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return true;
        }
        bitmap=downloadFromNet(url);
        if(bitmap!=null){
            imageCache.saveBitmap(url,bitmap);
            imageView.setImageBitmap(bitmap);
            return true;
        }
        return false;
    }

    public  void displayImage(String url, LoadImageListener loadImageListener){
        Bitmap bitmap;
        bitmap = imageCache.getBitmap(url);
        if (bitmap==null) bitmap=downloadFromNet(url);
        if (bitmap!=null){
            if (loadImageListener!=null)loadImageListener.success(bitmap);
        }else{
            if (loadImageListener!=null)loadImageListener.fail("bitmap is null");
        }
    }

    /**
     * 网络下载图片
     * @param url 图片url
     * @return bitmap
     */
    private  Bitmap downloadFromNet(String url) {
//        TODO 这里先不实现，应该放在子线程处理,回调在主线程
        return imageCache.getBitmap(url);
    }

}
