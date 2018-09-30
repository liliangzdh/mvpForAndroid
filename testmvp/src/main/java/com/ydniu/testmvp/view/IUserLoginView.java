package com.ydniu.testmvp.view;

public interface IUserLoginView {

    String getName();

    String getPassword();

    void clearName();

    void clearPassword();

    void showDialog();

    void dismissDialog();

    void showFailError(String error);

    void success();


}
