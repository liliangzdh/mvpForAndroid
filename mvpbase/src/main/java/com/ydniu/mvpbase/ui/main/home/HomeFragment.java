package com.ydniu.mvpbase.ui.main.home;

import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseFragment;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeView {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    public static HomeFragment getInstance(String mTitle) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
}
