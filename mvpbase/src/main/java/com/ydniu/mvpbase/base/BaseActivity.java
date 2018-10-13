package com.ydniu.mvpbase.base;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.ydniu.mvpbase.ui.views.LoadingProgressDialog;
import com.ydniu.mvpbase.utils.MPermissionUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {


    private Unbinder bind;

    ProgressDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        bind = ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent != null){
            getIntentData(intent);
        }
        createLoadingDialog();
        initViews();
        initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    public abstract @LayoutRes int getLayoutResId();


    public abstract void getIntentData(Intent intent);

    /**
     * 初始化View的代码写在这个方法中
     */
    public abstract void initViews();

    public abstract void initData();


    private void createLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingProgressDialog(this);
        }
    }


    public void showOrHideLoading(boolean flag) {
        if (flag) {
            loadingDialog.show();
        } else {
            loadingDialog.dismiss();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
