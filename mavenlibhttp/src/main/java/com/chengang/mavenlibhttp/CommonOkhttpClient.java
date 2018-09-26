package com.chengang.mavenlibhttp;

import com.chengang.mavenlibhttp.Listener.DisposeDataHandler;
import com.chengang.mavenlibhttp.https.HttpsUtils;
import com.chengang.mavenlibhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CommonOkhttpClient {

    public static OkHttpClient client;
    public static final int TIME_OUT= 20;

    /**
     * okhttp初始化配置
     */
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT,TimeUnit.SECONDS)//超时
                //允许重定向
                .followRedirects(true)
                //添加https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

         client = builder.build();

    }

    public static Call sendRequest(Request request, CommonJsonCallback commonJsonCallback){
        Call call=client.newCall(request);
        call.enqueue(commonJsonCallback);
        return call;
    }

   public static Call get(Request request, DisposeDataHandler handler){
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
   }

   public static Call post(Request request,DisposeDataHandler handler){
        Call call =client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
   }
}
