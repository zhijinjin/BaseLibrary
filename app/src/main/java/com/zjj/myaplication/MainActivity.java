package com.zjj.myaplication;

import android.os.Bundle;

import com.zjj.baselibrary.activity.BaseHttpActivity;
import com.zjj.baselibrary.http.HttpResult;
import com.zjj.baselibrary.http.ParameterMap;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseHttpActivity {

    String url = "";   //地址
    ParameterMap paramets = new ParameterMap();  //参数
    String body = "";  //请求体
    String charset = "application/json; charset=utf-8"; //请求体格式
    Map<String,String> customeHeads = new HashMap<>();  //请求头

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        httpGet(url,paramets,1);
        httpGet(url,2);

        httpPost(url,paramets,3);
        httpPost(url,paramets,customeHeads,4);
        httpPost(url,paramets,customeHeads,charset,5);
        httpPost(url,body,6);
        httpPost(url,body,customeHeads,7);
        httpPost(url,body,customeHeads,charset,8);

        httpPut(url,paramets,9);
        httpPut(url,paramets,customeHeads,10);
        httpPut(url,paramets,customeHeads,charset,11);
        httpPut(url,body,12);
        httpPut(url,body,customeHeads,13);
        httpPut(url,body,customeHeads,charset,14);
    }

    @Override
    public void onHttpFailure(HttpResult result, int id) {
        super.onHttpFailure(result, id);
        switch (id){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
        }
    }

    @Override
    public void onHttpResponse(HttpResult result, int id) {
        super.onHttpResponse(result, id);
        switch (id){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
        }
    }
}
