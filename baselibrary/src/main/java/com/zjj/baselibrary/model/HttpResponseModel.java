package com.zjj.baselibrary.model;

import com.zjj.baselibrary.http.HttpResult;

/**
 * 网络请求成功实体类
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/4/10.
 */

public class HttpResponseModel {
    private HttpResult result;
    private int id;

    public HttpResponseModel(HttpResult result, int id){
        this.id = id;
        this.result = result;
    }

    public HttpResult getResult() {
        return result;
    }

    public void setResult(HttpResult result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
