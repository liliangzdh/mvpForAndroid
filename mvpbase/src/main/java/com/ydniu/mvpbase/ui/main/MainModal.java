package com.ydniu.mvpbase.ui.main;

import com.ydniu.mvpbase.mvpbase.BaseModal;

public class MainModal extends BaseModal implements MainContract.Modal {



    //这应该是网络请求数据 这里就先不请求了。
    @Override
    public String getGankData() {
        return "hell world ";
    }
}
