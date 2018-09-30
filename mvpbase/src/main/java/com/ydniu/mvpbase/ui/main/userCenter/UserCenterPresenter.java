package com.ydniu.mvpbase.ui.main.userCenter;

import com.ydniu.mvpbase.mvpbase.BasePresenter;

public class UserCenterPresenter extends BasePresenter<UserCenterContract.UserCenterModal,UserCenterContract.UserCenterView>  {
    @Override
    public UserCenterContract.UserCenterModal createModal() {
        return new UserCenterModal();
    }


}
