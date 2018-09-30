package com.ydniu.mvpbase.ui.main;

import com.ydniu.mvpbase.mvpbase.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.Modal,MainContract.View> {

    @Override
    public MainContract.Modal createModal() {
        return new MainModal();
    }

    public void requestData(){

    }
}
