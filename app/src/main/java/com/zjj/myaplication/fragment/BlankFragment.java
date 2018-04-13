package com.zjj.myaplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjj.baselibrary.activity.BaseHttpActivity;
import com.zjj.baselibrary.fragment.BaseHttpFragment;
import com.zjj.baselibrary.http.HttpResult;
import com.zjj.baselibrary.http.ParameterMap;
import com.zjj.myaplication.R;

import java.util.HashMap;
import java.util.Map;

public class BlankFragment extends BaseHttpFragment {

    String url = "";
    String url1 = "http://47.94.222.147:8192/api/blocks/getheight";   //高度地址
    String url2 = "http://47.94.222.147:8192/api/accounts/open/";   //登陆地址
    String url3 = "http://47.94.222.147:8192/api/delegates";   //地址
    ParameterMap paramets = new ParameterMap();  //参数
    String body = "";  //请求体
    String charset = "application/json; charset=utf-8"; //请求体格式
    Map<String,String> customeHeads = new HashMap<>();  //请求头

    TextView tv_1,tv_2,tv_3;

    public BlankFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView =  inflater.inflate(R.layout.fragment_blank, container, false);
        tv_1 = contentView.findViewById(R.id.tv_1);
        tv_2 = contentView.findViewById(R.id.tv_2);
        tv_3 = contentView.findViewById(R.id.tv_3);

        httpGet(url1,2);

        ParameterMap paramets3 = new ParameterMap();  //参数
        paramets3.put("secret","lawsuit laptop quote debate fragile december blood pet wish auction horn private");
        httpPost(url2,paramets3,3);

        ParameterMap paramets4 = new ParameterMap();  //参数
        paramets4.put("secret","lawsuit laptop quote debate fragile december blood pet wish auction horn private");
        paramets4.put("username","shuai_ge");
        httpPut(url3,paramets4,4);

        return contentView;
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
                tv_3.setText(id+result.getError().toString());
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
                tv_3.setText(result.getRawJson().toString());
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
