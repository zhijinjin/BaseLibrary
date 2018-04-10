package com.zjj.baselibrary.http;

/**
 * MyOkHttp2 回调接口
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/4/10.
 */

public  interface  MyCallBack {

    void onFailure(HttpResult result,int id);

    void onResponse(HttpResult result,int id);
}
