package com.ydniu.mvpbase.ui.main.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseFragment;
import com.ydniu.mvpbase.ui.TextAct;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeView {


    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_useName)
    EditText et_userName;

    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_clear)
    Button bt_clear;

    @BindView(R.id.pb_load)
    ProgressBar progressBar;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.bt_clear, R.id.bt_login,R.id.bt_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:
                presenter.clear();
                break;
            case R.id.bt_login:
                presenter.login();
                break;
            case R.id.bt_go:
                startActivity(new Intent(getContext(), TextAct.class));
                break;
        }
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    public static HomeFragment getInstance(String mTitle) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public String getName() {
        return et_userName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString().trim();
    }

    @Override
    public void clearUserName() {
        et_userName.setText("");
    }

    @Override
    public void clearEditTextPassWord() {
        et_password.setText("");
    }
}
