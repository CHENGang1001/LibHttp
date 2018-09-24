package com.chengang.libhttp.request;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chengang on 2018/9/23.
 */

public class RequestParams {
    public ConcurrentHashMap<String ,String> urlHashMap = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String ,Object> fileHashMap = new ConcurrentHashMap<>();

    /**
     * Constructs null
     */
    public RequestParams() {
        this((Map<String,String>) null);
    }

    /**
     * Constructs
     * @param stringStringMap
     */
    public RequestParams(Map<String, String> stringStringMap) {
        if (stringStringMap!=null){
            for (Map.Entry<String,String> source :stringStringMap.entrySet()){
                put(source.getKey(),source.getValue());
            }
        }
    }

    public RequestParams(final String key, final String value) {
        this(new HashMap<String ,String>(){
            {
                put(key,value);
            }
        });
    }

    public void put(String key, String value) {
        if (key!=null&&value!=null){
            urlHashMap.put(key,value);
        }
    }

    public void put(String key,Object object)throws FileNotFoundException{
        if (key!=null&&object!=null){
            fileHashMap.put(key,object);
        }
    }

    public boolean isHasParams(){
        if (urlHashMap.size()>0||fileHashMap.size()>0){
            return true;
        }
        return false;
    }
}
