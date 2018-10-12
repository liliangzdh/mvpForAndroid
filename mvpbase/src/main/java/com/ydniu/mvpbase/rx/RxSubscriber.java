package com.ydniu.mvpbase.rx;

import android.accounts.NetworkErrorException;

import com.ydniu.mvpbase.exception.ServerException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RxSubscriber<T> implements Observer<T> {

    public abstract void _onNext(T t);

    public abstract void _onError(String msg, boolean isNetError);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof NetworkErrorException
                || e instanceof UnknownHostException) {
            _onError(ServerException.handleException(e).getMessage(), true);
        } else {
            _onError(ServerException.handleException(e).getMessage(), false);
        }
    }

    @Override
    public void onComplete() {

    }
}
