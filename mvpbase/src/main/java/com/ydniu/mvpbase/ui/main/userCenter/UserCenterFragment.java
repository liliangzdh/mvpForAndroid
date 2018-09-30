package com.ydniu.mvpbase.ui.main.userCenter;

import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseFragment;

public class UserCenterFragment extends BaseFragment<UserCenterPresenter> {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public UserCenterPresenter createPresenter() {
        return new UserCenterPresenter();
    }

    public static UserCenterFragment getInstance(String mTitle) {
        return new UserCenterFragment();
    }
}
