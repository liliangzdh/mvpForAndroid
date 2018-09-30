package com.ydniu.mvpbase.mvpbase;

public abstract class BasePresenter<M extends IModal,V extends IView> implements IPresenter<V> {

    private M modal;

    private V view;

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
}
