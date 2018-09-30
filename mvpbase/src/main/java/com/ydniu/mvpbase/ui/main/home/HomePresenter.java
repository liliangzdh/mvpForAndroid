package com.ydniu.mvpbase.ui.main.home;

import com.ydniu.mvpbase.mvpbase.BasePresenter;

public class HomePresenter extends BasePresenter<HomeContract.HomeModal, HomeContract.HomeView> {
    @Override
    public HomeContract.HomeModal createModal() {
        return new HomeModal();
    }
}
