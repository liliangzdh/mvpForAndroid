package com.ydniu.mvpbase.ui.main.home;

import android.text.TextUtils;

import com.ydniu.mvpbase.interf.GetRequest_Interface;
import com.ydniu.mvpbase.mvpbase.BasePresenter;
import com.ydniu.mvpbase.utils.LogUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePresenter extends BasePresenter<HomeContract.HomeModal, HomeContract.HomeView> {
    @Override
    public HomeContract.HomeModal createModal() {
        return new HomeModal();
    }

    //
    public void clear() {
        getView().clearEditTextPassWord();
        getView().clearUserName();
    }

    public void login() {
        String name = getView().getName();
        String password = getView().getPassword();

        if (TextUtils.isEmpty(name)) {
            getView().showToast("用户不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            getView().showToast("密码不能为空");
            return;
        }

        //这里就要发起网络请求了。(使用rxJava,  retrofit)
        // modal 封装网络请求   retrofit 结合 rxjava
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //如果回调用到了 rxjava的回调，就必须用到这个
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);

        // 以为call的形式返回的
//        Call<ResponseBody> call = getRequest_interface.getCall();
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ResponseBody body = response.body();
//                if (body != null) {
//                    try {
//                        String string = body.string();
//                        LogUtils.d(string);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                LogUtils.d(t.getMessage());
//            }
//        });

        /** 结合rxjava 回调到主线程 */
//        getRequest_interface.getCallData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        LogUtils.d("onSubscribe=======");
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        LogUtils.d("onNext=======");
//                        try {
//                            LogUtils.d(responseBody.string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.d("onError" + e.getMessage() + ":" + e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LogUtils.d("onComplete");
//                    }
//                });

        //更简单的方式，compose
        getRequest_interface.getCallData()
                .compose(new ObservableTransformer<ResponseBody, ResponseBody>() {
                    @Override
                    public ObservableSource<ResponseBody> apply(Observable<ResponseBody> observable) {
                        return observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            LogUtils.d(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
