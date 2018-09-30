package com.ydniu.mvpbase.ui.main.find;

import com.ydniu.mvpbase.mvpbase.BasePresenter;

public class FindPresenter extends BasePresenter<FindContract.FindModal,FindContract.FindView> {
    @Override
    public FindContract.FindModal createModal() {
        return new FindModal();
    }
}
