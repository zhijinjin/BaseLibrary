package com.zjj.baselibrary.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.zjj.baselibrary.http.HttpResult;
import com.zjj.baselibrary.http.MyOkHttp2;
import com.zjj.baselibrary.http.ParameterMap;
import com.zjj.baselibrary.model.HttpResponseModel;
import com.zjj.baselibrary.model.HttpFailureModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;


/**
 * 包含网络请求方法，使用 Callback 进行网络请求回调，网络请求使用MyOkHttp2
 * Created by zhijinjin on 2018/3/28.
 */
public class BaseHttpActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * post请求
     * @param url
     * @param parameters
     * @param customeHeads
     * @param charset
     * @param id
     */
    public void httpPost(String url, ParameterMap parameters, Map<String,String> customeHeads, String charset, final int id){
        MyOkHttp2.post(url,parameters,customeHeads,charset,id);
    }

    public void httpPost(String url, ParameterMap parameters, Map<String,String> customeHeads,final int id){
        httpPost(url,parameters,customeHeads,null,id);
    }

    public void httpPost(String url, ParameterMap parameters,final int id){
        httpPost(url,parameters,null,id);
    }

    public void httpPost(String url, String requestBody, Map<String,String> customeHeads, String charset, final int id){
        MyOkHttp2.post(url,requestBody,customeHeads,charset,id);
    }

    public void httpPost(String url, String requestBody, Map<String,String> customeHeads, final int id){
        httpPost(url,requestBody,customeHeads,null,id);
    }

    public void httpPost(String url, String requestBody,final int id){
        httpPost(url,requestBody,null,id);
    }


    /**
     * put 请求
     * @param url
     * @param parameters
     * @param customeHeads
     * @param charset
     * @param id
     */
    public void httpPut(String url, ParameterMap parameters, Map<String,String> customeHeads, String charset, final int id){
        MyOkHttp2.put(url,parameters,customeHeads,charset,id);
    }

    public void httpPut(String url, ParameterMap parameters, Map<String,String> customeHeads,final int id){
        httpPut(url,parameters,customeHeads,null,id);
    }

    public void httpPut(String url, ParameterMap parameters,final int id){
        httpPut(url,parameters,null,id);
    }

    public void httpPut(String url, String requestBody, Map<String,String> customeHeads, String charset, final int id){
        MyOkHttp2.put(url,requestBody,customeHeads,charset,id);
    }

    public void httpPut(String url, String requestBody, Map<String,String> customeHeads, final int id){
        httpPut(url,requestBody,customeHeads,null,id);
    }

    public void httpPut(String url, String requestBody,final int id){
        httpPut(url,requestBody,null,id);
    }


    /**
     * get 请求
     * @param url
     * @param parameters
     * @param id
     */
    public void httpGet(String url, ParameterMap parameters, int id){
        MyOkHttp2.get(url,parameters,id);
    }

    public void httpGet(String url,  int id){
        httpGet(url,null,id);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showResponse(HttpResponseModel msg){
        onHttpResponse(msg.getResult(),msg.getId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showFailure(HttpFailureModel msg){
        onHttpFailure(msg.getResult(),msg.getId());
    }

    /**
     * 请求成功
     * @param result
     * @param id
     */
    public void onHttpResponse(HttpResult result, int id){

    }
    /**
     * 请求失败
     * @param result
     * @param id
     */
    public void onHttpFailure(HttpResult result, int id){

    }



}
