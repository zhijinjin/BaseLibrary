package com.zjj.baselibrary.http;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 网络请求参数
 * Created by zhijinjin on 2018/3/28.
 */

public class ParameterMap  {

    //使用HashMap存储参数
    private Map<String, Object> map = new HashMap<>();

    /**
     * 根据key获取参数值
     * @param key
     * @return Object
     */
    public Object get(String key){
        return map.get(key);
    }

    /**
     * 根据key获取参数 String值
     * @param key
     * @return String
     */
    public String getString(String key){
        return containsKey(key) ? map.get(key).toString() : null;
    }

    /**
     * 添加请求参数
     * @param key
     * @param value
     * @return
     */
    public ParameterMap put(String key, Object value){
        map.put(key, value);
        return this;
    }

    /**
     * 添加请求参数
     * @param map
     * @return
     */
    public ParameterMap putAll(Map<String, Object> map){
        this.map.putAll(map);
        return this;
    }

    /**
     * 判断是否包含对应参数
     * @param key
     * @return
     */
    public boolean containsKey(String key){
        return map.containsKey(key);
    }

    public Map<String, Object> getMap(){
        Map<String, Object> result = new HashMap<>();
        result.putAll(this.map);

        return result;
    }

    /**
     * 请求参数转成 JsonString
     * @return
     */
    public String toJSONString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        return jsonObject.toString();
    }

    /**
     * 请求参数转成 请求格式
     * @return
     */
    public String toQueryString(){
        List<String> parameterList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            parameterList.add(entry.getKey() + "=" + (entry.getValue() == null ? "" : entry.getValue().toString()));
        }
        return  join("&", parameterList);
    }

    private static String join(CharSequence delimiter, List<String> elements) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            if(i==(elements.size()-1)){
                builder.append(elements.get(i));
            }else{
                builder.append(elements.get(i));
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
}
