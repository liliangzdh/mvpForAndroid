package com.ydniu.testmvp.biz;

import com.ydniu.testmvp.bean.User;

//登录之后的回调 (成功或者失败)
public interface OnLoginListener {



    void loginSuccess(User user);

    void loginFailed(String error);


}
