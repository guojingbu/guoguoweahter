package com.jiuwu.guojingbu.guoguoweather.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author guojingbu
 */

public class HttpClient {
    /**
     * 常量tag
     */
    private static final String tag = "HttpClient";
    /**
     * 本类实体引用
     */
    private static HttpClient mInstance;
    /**
     * okhttp引用
     */
    private OkHttpClient mOkHttpClient;

    /**
     * 内容类型的请求
     */
    public static String authorization = "";

    private HttpClient() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        mOkHttpClient = okhttpBuilder.connectTimeout(HttpConfig.DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 获取httpClient实例
     *
     * @return
     */
    public static synchronized HttpClient getInstance() {
        if (mInstance == null) {
            mInstance = new HttpClient();
        }
        return mInstance;
    }

    /**
     * 发送一个get请求
     *
     * @param url
     * @param callback
     */
    public void SendOkHttpRequest(String url, final JsonCallback callback) {
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.postFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.postSuccess(response);
            }
        });
    }
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * post请求
     *
     * @param url
     * @param requestjson
     * @param callback
     */
    public void asyncJsonRequest(String url, String requestjson, final JsonCallback callback) {
        Request.Builder requestBuilder = new Request.Builder()
                .addHeader(HttpConfig.HEADER_ACCEPT, "application/json")
                .addHeader(HttpConfig.HEADER_CONTENT_TYPE,
                        HttpConfig.PROTOCOL_CONTENT_TYPE)
                .addHeader(HttpConfig.HEADER_AUTHORIZATION,
                        "Basic" + " " + authorization).url(url)
                .post(buildRequestBody(requestjson));

        Request request = requestBuilder.build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.postFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.postSuccess(response);
            }
        });
        /**
         * 创建请求体
         *
         * @param request
         * @return
         */


    }

    public static RequestBody buildRequestBody(String request) {
        return RequestBody.create(HttpConfig.mMediaType, request);
    }
}