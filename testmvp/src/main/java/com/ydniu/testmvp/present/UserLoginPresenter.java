package com.ydniu.testmvp.present;

import android.os.Handler;

import com.ydniu.testmvp.bean.User;
import com.ydniu.testmvp.biz.IUserBiz;
import com.ydniu.testmvp.biz.OnLoginListener;
import com.ydniu.testmvp.biz.UserBiz;
import com.ydniu.testmvp.view.IUserLoginView;

public class UserLoginPresenter {

    IUserLoginView iUserLoginView;
    IUserBiz userBiz;


    Handler handler = new Handler();

    public UserLoginPresenter(IUserLoginView iUserLoginView) {
        this.iUserLoginView = iUserLoginView;
        this.userBiz = new UserBiz();
    }


    public void login(){
        //业务逻辑操作
        this.iUserLoginView.showDialog();


        this.userBiz.login(this.iUserLoginView.getName(), this.iUserLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                //这个在子线程中

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.dismissDialog();
                        iUserLoginView.success();
                    }
                });

            }

            @Override
            public void loginFailed(final String error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.dismissDialog();
                        iUserLoginView.showFailError(error);

                    }
                });
            }
        });


    }


    public void clear(){
        //业务逻辑操作
        this.iUserLoginView.clearName();
        this.iUserLoginView.clearPassword();

    }

}
