package com.ydniu.mvpbase.mvpbase;

public interface IPresenter<V extends IView> {


    void attachView(V view);

    void detachView();
}
