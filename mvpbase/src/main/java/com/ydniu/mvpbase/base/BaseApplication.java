package com.ydniu.mvpbase.base;

import android.app.Application;

import com.ydniu.mvpbase.utils.LogUtils;

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //logger 初始化 ,不然不会打印
        LogUtils.init("liliangzdh:",true);

    }
}
