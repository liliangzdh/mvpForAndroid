package com.ydniu.mvpbase.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.rx.RxSchedulersUtils;
import com.ydniu.mvpbase.rx.RxSubscriber;
import com.ydniu.mvpbase.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class TextAct extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find);

        init();
    }


    public void init() {

        /** 把这个封装起来 **/
        //  .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .compose(this.<Long>bindToLifecycle())

//        Observable.interval(1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                //避免内存泄漏
//                .compose(this.<Long>bindToLifecycle())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        LogUtils.d("接收数据" + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        //这个比上面 封装了 点小东西
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(RxSchedulersUtils.<Long>applySchedulers(this))
                .subscribe(new RxSubscriber<Long>(){
                    @Override
                    public void _onNext(Long aLong) {
                        LogUtils.d("a:"+aLong);
                    }

                    @Override
                    public void _onError(String msg, boolean isNetError) {

                    }
                });


        // 开启一个县城

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                LogUtils.d("asa");
                emitter.onNext("wa");
                emitter.onComplete();
            }
        })
                .compose(RxSchedulersUtils.applySchedulers(this))
                .subscribe(new RxSubscriber<Object>(){

                    @Override
                    public void _onNext(Object o) {
                        LogUtils.d("hello :");
                    }

                    @Override
                    public void _onError(String msg, boolean isNetError) {

                    }
                });
    }


}
