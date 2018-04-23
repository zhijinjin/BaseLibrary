package com.zjj.baselibrary.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class MyOkHttp {

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
    protected static HttpResult rawPost(String url, String requestBody, Map<String,String> customeHeads, String charset )  {
        try {
            OkHttpClient httpClient = getHttpClient();
            RequestBody body =createReqestBody(requestBody, charset);
            Request.Builder builder=new Request.Builder();
            addHeads(customeHeads, builder);
            Request request = builder.url(url)
                    .post(body)
                    .build();

            Response response = httpClient.newCall(request).execute();
            return HttpResult.FromJsonString( getResponseContent(response));
        }catch (IOException e){
            String errorInfo = String.format("Exception when put,url:%s,data:%s", url, requestBody);
            System.out.println(errorInfo);
            e.printStackTrace();
            return HttpResult.Failed(e);
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
    public static HttpResult post(String url, String requestBody,  Map<String, String> customeHeaders, String charset){
        return rawPost(url, requestBody, customeHeaders, charset);
    }

    /**
     * post请求
     * @param url
     * @param parameters  Map类型请求体，请求时候转成相应JSONObject
     * @param customeHeads
     * @param charset
     * @return
     */
    public static HttpResult post(String url, ParameterMap parameters, Map<String,String> customeHeads, String charset) {
        String parameterString = parameters == null ? "" : parameters.toJSONString();
        return rawPost(url, parameterString, customeHeads, charset);
    }

    public static HttpResult post(String url, String parameters, Map<String,String> customeHeads ) {
        return post(url, parameters, customeHeads, null);
    }

    public static HttpResult post(String url, ParameterMap parameters, Map<String,String> customeHeads ) {
        return post(url, parameters, customeHeads, null);
    }

    public static HttpResult post(String url, String parameters )  {
        return post(url, parameters, null, null);
    }

    public static HttpResult post(String url, ParameterMap parameters ) {
        String parametersString = parameters == null ? "" : parameters.toJSONString();
        return post(url, parametersString, null, null);
    }


    /**
     * put请求
     * @param url
     * @param requestBody
     * @param customeHeads
     * @param charset
     * @return
     */
    protected static HttpResult rawPut(String url, String requestBody, Map<String,String> customeHeads, String charset ){
        try {
            OkHttpClient httpClient = getHttpClient();
            RequestBody body =createReqestBody(requestBody, charset);
            Request.Builder builder=new Request.Builder();
            addHeads(customeHeads, builder);
            Request request = builder.url(url)
                    .put(body)
                    .build();

            Response response = httpClient.newCall(request).execute();
            return HttpResult.FromJsonString( getResponseContent(response) );
        }catch (IOException e){
            String errorInfo = String.format("Exception when put,url:%s,data:%s", url, requestBody);
            System.out.println(errorInfo);
            e.printStackTrace();
            return HttpResult.Failed(e);
        }
    }

    public static HttpResult put(String url, String requestBody,  Map<String, String> customeHeaders, String charset) {
        return rawPut(url, requestBody, customeHeaders, charset);
    }

    public static HttpResult put(String url, ParameterMap parameters, Map<String,String> customeHeads, String charset) {
        String parametersString = parameters == null ? "" : parameters.toJSONString();
        return put(url, parametersString, customeHeads, charset);
    }

    public static HttpResult put(String url, String parameStr, Map<String,String> customeHeads ) {
        return put(url, parameStr, customeHeads, null);
    }

    public static HttpResult put(String url, ParameterMap parameters, Map<String,String> customeHeads ) {
        return put(url, parameters, customeHeads, null);
    }

    public static HttpResult put(String url, String parameStr )  {
        return put(url, parameStr, null, null);
    }

    public static HttpResult put(String url, ParameterMap parameters ) {
        String parametersString = parameters == null ? "" : parameters.toJSONString();
        return put(url, parametersString, null, null);
    }

    private static HttpResult rawGet(String url, String queryString) {
        try {
            OkHttpClient httpClient = getHttpClient();
            String fullUrl = queryString == null ? url : url + "?" + queryString;
            Request.Builder builder=new Request.Builder();
            Request request = builder.url(encodeUrl(fullUrl))
                    .get()
                    .build();

            Response response = httpClient.newCall(request).execute();
            return  HttpResult.FromJsonString( getResponseContent(response) );
        }catch (IOException e){
            String errorInfo = String.format("Exception when put,url:%s,data:%s", url, queryString);
            System.out.println(errorInfo);
            e.printStackTrace();
            return HttpResult.Failed(e);
        }
    }


    public static HttpResult get(String url, ParameterMap parameters)  {
        String queryString = parameters == null ? null : parameters.toQueryString();
        return  rawGet(url, queryString) ;
    }

    public static HttpResult get(String url)  {
        return  get(url, null) ;
    }
}
