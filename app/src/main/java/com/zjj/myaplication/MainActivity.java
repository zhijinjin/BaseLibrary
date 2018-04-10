package com.zjj.myaplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.zjj.baselibrary.activity.BaseHttpActivity;
import com.zjj.baselibrary.http.HttpResult;
import com.zjj.baselibrary.http.ParameterMap;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseHttpActivity {

    String url = "";
    String url1 = "http://47.94.222.147:8192/api/blocks/getheight";   //地址
    String url2 = "http://47.94.222.147:8192/api/accounts/open/";   //登陆地址
    String url3 = "http://47.94.222.147:8192/api/blocks/getheight";   //地址
    ParameterMap paramets = new ParameterMap();  //参数
    String body = "";  //请求体
    String charset = "application/json; charset=utf-8"; //请求体格式
    Map<String,String> customeHeads = new HashMap<>();  //请求头

    TextView tv_1,tv_2,tv_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);

        httpGet(url1,2);

        ParameterMap paramets3 = new ParameterMap();  //参数
        paramets3.put("secret","lawsuit laptop quote debate fragile december blood pet wish auction horn private");
        httpPost(url2,paramets3,3);
    }



    private void loadData(){
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
                tv_1.setText(id+result.getError().toString());
                break;
            case 3:
                tv_2.setText(id+result.getError().toString());
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
                tv_1.setText(result.getRawJson().toString());
                break;
            case 3:
                tv_2.setText(result.getRawJson().toString());
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
