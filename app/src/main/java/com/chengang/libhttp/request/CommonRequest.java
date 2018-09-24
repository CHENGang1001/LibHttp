package com.chengang.libhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by chengang on 2018/9/23.
 */

public class CommonRequest {

    /**
     * get
     * @param url
     * @param params
     * @return
     */
    public static Request getRequest(String url ,RequestParams params){
        StringBuilder builder = new StringBuilder(url).append("?");
        if (params!=null){
            for (Map.Entry<String,String> entry :params.urlHashMap.entrySet()){
                builder .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        return new Request.Builder().url(builder.substring(0,builder.length()-1)).get().build();
    }

    /**
     * post FormBody
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url ,RequestParams params){
        FormBody.Builder  builder =new  FormBody.Builder();
        if (params!=null){
            for (Map.Entry<String,String>entry:params.urlHashMap.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        return new Request.Builder().post(body).url(url).build();
    }
}