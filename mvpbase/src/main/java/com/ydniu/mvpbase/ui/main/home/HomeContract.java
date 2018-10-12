package com.ydniu.mvpbase.ui.main.home;

import com.ydniu.mvpbase.mvpbase.IModal;
import com.ydniu.mvpbase.mvpbase.IView;

public interface HomeContract {

    interface HomeModal extends IModal {

    }

    interface HomeView extends IView {
        String getName();
        String getPassword();
        void clearUserName();

        void clearEditTextPassWord();

        void setData(String text);

    }
}
