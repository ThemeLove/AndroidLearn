package com.themelove.androidlearn.demo.retrofit;

import java.util.ArrayList;

/**
 * author:qingshanliao
 * date:2019/4/3
 */
public class DeliveryBean {
    String message;
    String nu;  //订单号
    String ischeck; //是否验收
    String condition;
    String com; //快递公司
    String status;  //快递状态
    String state;
    ArrayList<Step> data; //快递经过的所有站点集合

    /**
     * 快递的站点
     */
    public class Step{
         String time;
         String ftime;
         String context;
         String location;
    }
}
