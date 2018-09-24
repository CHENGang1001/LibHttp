package com.chengang.libhttp.Listener;

public class DisposeDataHandler {
    public DisposeDataListener listener=null;
    public Class<?>mClass = null;

    public DisposeDataHandler(DisposeDataListener listener) {
        this.listener = listener;
    }

    public DisposeDataHandler(DisposeDataListener listener, Class<?> mClass) {
        this.listener = listener;
        this.mClass = mClass;
    }
}
