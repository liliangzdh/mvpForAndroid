package com.ydniu.mvpbase.ui.main;

import com.ydniu.mvpbase.mvpbase.IModal;
import com.ydniu.mvpbase.mvpbase.IView;

/**
 * 契约类
 */
public interface MainContract {


    interface View extends IView {

        void showData(String str);
    }

    interface Modal extends IModal {
        String getGankData();
    }
}
