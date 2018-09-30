package com.ydniu.testmvp.biz;

import com.ydniu.testmvp.bean.User;

public class UserBiz implements IUserBiz {


    //逻辑操作 业务逻辑
    @Override
    public void login(final String name, final String password, final OnLoginListener onLoginListener) {
        //网络请求

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("liliang".equals(name) && "123456".equals(password)) {
                    //模拟 登录成功 返回数据
                    User user = new User();

                    user.setName(name);
                    user.setPassword(password);
                    onLoginListener.loginSuccess(user);

                } else {
                    onLoginListener.loginFailed("数据错误");
                }
            }
        }).start();
    }
}
