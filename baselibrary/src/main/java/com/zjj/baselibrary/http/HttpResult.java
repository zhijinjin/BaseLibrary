package com.zjj.baselibrary.http;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class HttpResult {

    //请求成功与否
    private boolean successful = false;
    //错误信息
    private String error = null;
    //请求返回的原始 JsonString
    private String rawJson = null;
    //异常
    private Exception exception;
    //请求返回的原始 JsonString 转成的 JSONObject
    private JSONObject jsonObject;

    public boolean isSuccessful() {
        return successful;
    }

    public String getError() {
        return error;
    }

    public String getRawJson() {
        return rawJson;
    }
    public Exception getException() {
        return exception;
    }

    public boolean isLocalError(){
        return exception != null;
    }

    public Map<String, Object> parseMap(){
        return JSONObject.parseObject(rawJson);
    }

    public <T> T parseObject(Class<T>  classzz){
        return jsonObject == null ?
                null :
                jsonObject.toJavaObject(classzz);
    }

    public String toString(){
        return String.format("success:%s, error:%s, exception:%s rawJson:%s",
                this.successful,
                this.error,
                this.exception,
                this.rawJson);
    }

    /**
     * 请求结果格式化
     * @param jsonString
     * @return
     */
    public static HttpResult FromJsonString(String jsonString){
        HttpResult result = new HttpResult();
        result.rawJson = jsonString;
        result.jsonObject = JSONObject.parseObject(jsonString);
        JSONObject json = result.jsonObject;
        if (json.containsKey("success")){
            result.successful = json.getBoolean("success");
        }
        if (json.containsKey("error")){
            result.error = json.getString("error");
        }
        return result;
    }

    /**
     * 请求异常 转 请求结果
     * @param ex
     * @return
     */
    public static HttpResult Failed(Exception ex){
        HttpResult result = new HttpResult();
        result.successful = false;
        result.error = ex.getMessage();
        result.exception = ex;

        return result;
    }
}
