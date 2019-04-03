package com.themelove.androidlearn.demo.retrofit;

/**
 * author:qingshanliao
 * date:2019/4/3
 */
public class  HttpResult<T> {
    public int code;

    public String message;
    public T date;

    public T getData(){
        return date;
    }

    public boolean isSuccess(){
        return code==200;
    }
}
