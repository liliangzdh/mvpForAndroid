package com.ydniu.testmvp.biz;

public interface IUserBiz {

    public void login(String name,String password,OnLoginListener onLoginListener);
}
