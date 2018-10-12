package com.ydniu.mvpbase.mvpbase;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M extends IModal,V extends IView> implements IPresenter<V> {

    private M modal;

    private V view;

    private CompositeDisposable compositeDisposable;

    public V getView(){
        return view;
    }


    public  M getModal(){
        return  this.modal ;
    }

    public BasePresenter(){
        this.modal = createModal();
    }

    public  abstract M createModal() ;


    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }


    public boolean isViewAttached(){
        return this.view != null;
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务，避免内存泄漏(框架已自行处理)
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);// 将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();// 保证 Activity 结束时取消所有正在执行的订阅
        }
    }

    protected <T> LifecycleProvider<T> getLifecycleProvider() {
        LifecycleProvider<T> provider = null;
        if (null != view && view instanceof LifecycleProvider) {
            provider = (LifecycleProvider<T>) view;
        }
        return provider;
    }
}
