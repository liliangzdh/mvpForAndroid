package com.ydniu.mvpbase.ui.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.ydniu.mvpbase.R;

public class LoadingProgressDialog extends ProgressDialog {

    public LoadingProgressDialog(Context context) {
        super(context,R.style.Progress_Dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
    }


}
