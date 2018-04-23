package com.zjj.baselibrary.http;

import com.zjj.baselibrary.model.HttpResponseModel;
import com.zjj.baselibrary.model.HttpFailureModel;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class MyOkHttp2 {

    private static OkHttpClient client;

    protected static OkHttpClient getHttpClient(){
        if(client==null){
            //网络请求拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);// 记录请求和响应行及其各自的标题和正文（如果存在）

            client = new OkHttpClient.Builder()
//                    .addNetworkInterceptor(loggingInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .build();

        }
        return client;
    }

    /**
     * 添加请求头
     * @param heads
     * @param builder
     */
    protected static void addHeads(Map<String, String> heads, Request.Builder builder) {
        if (null == heads )
            return;
        for (Map.Entry<String, String> entry : heads.entrySet()){
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    protected static RequestBody createReqestBody(String parameterString) throws UnsupportedEncodingException {
        return createReqestBody(parameterString,null);
    }

    /**
     * 创建请求体
     * @param parameterString
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    protected static RequestBody createReqestBody(String parameterString, String charset) throws UnsupportedEncodingException {
        MediaType contentType = MediaType.parse(charset==null?"application/json; charset=utf-8":charset);
        RequestBody body=RequestBody.create(contentType, parameterString);
        return body;
    }

    /**
     * 处理请求结果
     * @param response
     * @return
     * @throws IOException
     */
    protected static String getResponseContent(Response response) throws IOException{
        if (null == response)
            throw new IOException("Get response failed");
        String content = response.body().string(); //EntityUtils.toString(response.getEntity());
        if (!content.contains("\"success\":")){
            throw new IOException(String.format("Http server response failed, code:%d, reason:%s.\n content:%s",
                    response.code(),
                    content));
        }
        return content;
    }

    /**
     * 编码请求地址
     * @param url
     * @return
     * @throws IOException
     */
    protected static String encodeUrl(String url)throws IOException{
        try{
            URL uri = new URL(url);
            return new URI(uri.getProtocol(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    uri.getPath(), uri.getQuery(), null)
                    .toString();
        }catch(Exception ex){
            throw new IOException("invalid url: "+ url, ex);
        }
    }

    /**
     * @param url
     * @param requestBody
     * @param customeHeads
     * @param charset
     * @return
     */
    protected static void rawPost(String url, String requestBody, Map<String,String> customeHeads, String charset,final int id )  {
        try {
            OkHttpClient httpClient = getHttpClient();
            RequestBody body =createReqestBody(requestBody, charset);
            Request.Builder builder=new Request.Builder();
            addHeads(customeHeads, builder);
            Request request = builder.url(url)
                    .post(body)
                    .build();

            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sentFailure(e,id);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        sentResponse(HttpResult.FromJsonString( getResponseContent(response)),id);
                    }catch (IOException e){
                        sentFailure(e,id);
                    }
                }
            });
        }catch (IOException e){
            sentFailure(e,id);
        }
    }

    /**
     * post请求
     * @param url
     * @param requestBody  支持String 类型请求体
     * @param customeHeaders  请求头
     * @param charset 请求体格式
     * @return
     */
    public static void post(String url, String requestBody,  Map<String, String> customeHeaders, String charset,final int id){
         rawPost(url, requestBody, customeHeaders, charset,id);
    }

    /**
     * post请求
     * @param url
     * @param parameters  Map类型请求体，请求时候转成相应JSONObject
     * @param customeHeads
     * @param charset
     * @return
     */
    public static void post(String url, ParameterMap parameters, Map<String,String> customeHeads, String charset,final int id) {
        String parameterString = parameters == null ? "" : parameters.toJSONString();
         rawPost(url, parameterString, customeHeads, charset,id);
    }

    /**
     * put请求
     * @param url
     * @param requestBody
     * @param customeHeads
     * @param charset
     * @return
     */
    protected static void rawPut(String url, String requestBody, Map<String,String> customeHeads, String charset,final int id ){
        try {
            OkHttpClient httpClient = getHttpClient();
            RequestBody body =createReqestBody(requestBody, charset);
            Request.Builder builder=new Request.Builder();
            addHeads(customeHeads, builder);
            Request request = builder.url(url)
                    .put(body)
                    .build();

            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sentFailure(e,id);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        sentResponse(HttpResult.FromJsonString( getResponseContent(response)),id);
                    }catch (IOException e){
                        sentFailure(e,id);
                    }
                }
            });
        }catch (IOException e){
            sentFailure(e,id);
        }
    }

    public static void put(String url, String requestBody,  Map<String, String> customeHeaders, String charset,final int id) {
        rawPut(url, requestBody, customeHeaders, charset,id);
    }

    public static void put(String url, ParameterMap parameters, Map<String,String> customeHeads, String charset,final int id) {
        String parametersString = parameters == null ? "" : parameters.toJSONString();
        put(url, parametersString, customeHeads, charset,id);
    }

    private static void rawGet(final String url, final String queryString,final int id) {
        try {
            OkHttpClient httpClient = getHttpClient();
            String fullUrl = queryString == null ? url : url + "?" + queryString;
            Request.Builder builder=new Request.Builder();
            Request request = builder.url(encodeUrl(fullUrl))
                    .get()
                    .build();

            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sentFailure(e,id);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        sentResponse(HttpResult.FromJsonString( getResponseContent(response)),id);
                    }catch (IOException e){
                        sentFailure(e,id);
                    }
                }
            });
        }catch (IOException e){
            sentFailure(e,id);
        }
    }

    public static void get(String url, ParameterMap parameters, int id)  {
        String queryString = parameters == null ? null : parameters.toQueryString();
        rawGet(url, queryString,id) ;
    }

    public static void get(String url, int id)  {
         get(url, null,id) ;
    }

    private static void sentResponse(HttpResult result ,int id){
        HttpResponseModel eventModel = new HttpResponseModel(result,id);
        EventBus.getDefault().post(eventModel);
    }

    private static void sentFailure(Exception e,int id){
        HttpFailureModel eventModel = new HttpFailureModel(HttpResult.Failed(e),id);
        EventBus.getDefault().post(eventModel);
    }

}
