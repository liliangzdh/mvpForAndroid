package com.ydniu.mvpbase.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ydniu.mvpbase.mvpbase.BasePresenter;
import com.ydniu.mvpbase.mvpbase.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IView {


    private T presenter;

    /**
     * 缓存Fragment view
     */
    private View mRootView;
    private Unbinder bind;

    private BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = View.inflate(getContext(), getLayoutResId(), null);
            bind = ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    public abstract @LayoutRes
    int getLayoutResId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) this.getActivity();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void showOrHideLoading(boolean flag) {
        mActivity.showOrHideLoading(flag);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }

        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    public abstract T createPresenter();
}
