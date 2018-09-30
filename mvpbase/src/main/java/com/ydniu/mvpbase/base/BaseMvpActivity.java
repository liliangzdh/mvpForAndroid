package com.ydniu.mvpbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.ydniu.mvpbase.mvpbase.BasePresenter;
import com.ydniu.mvpbase.mvpbase.IView;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IView {


    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showOrHideLoading(boolean flag) {
        super.showOrHideLoading(flag);
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public abstract T createPresenter();
}
