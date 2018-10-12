package com.ydniu.mvpbase.ui.main.find;

import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseFragment;

public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.FindView {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {

    }

    @Override
    public FindPresenter createPresenter() {
        return new FindPresenter();
    }

    public static FindFragment getInstance(String mTitle) {
        return new FindFragment();
    }
}
