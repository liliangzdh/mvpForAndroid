package com.ydniu.mvpbase.ui.main.userCenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ProgressBar;

import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseFragment;
import com.ydniu.mvpbase.net.download.DownLoadManager;
import com.ydniu.mvpbase.net.download.ProgressCallBack;
import com.ydniu.mvpbase.utils.IntentUtils;
import com.ydniu.mvpbase.utils.LogUtils;
import com.ydniu.mvpbase.utils.MPermissionUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class UserCenterFragment extends BaseFragment<UserCenterPresenter> {


    @BindView(R.id.pb)
    ProgressBar progressBar;

    public final int REQUEST_CODE = 10;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.bt_down})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_down:
                MPermissionUtils.requestPermissionsResult(this, 1, new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}
                        , new MPermissionUtils.OnPermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                showToast("权限获取成功");
                                down();
                            }

                            @Override
                            public void onPermissionDenied() {
                                MPermissionUtils.showTipsDialog(getContext());
                            }
                        });

                break;
        }
    }

    File file;

    public void down() {
        final String destFileDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String destFileName = "xm_ydniu.apk";
        DownLoadManager.getInstance();
        DownLoadManager.load("http://update.ydniu.com/android/old/flcp_xm_ydniu.apk", new ProgressCallBack(destFileDir, destFileName) {
            @Override
            public void onSuccess(Object o) {
                try {
                    file = new File(destFileDir, destFileName);
                    //安装apk，判断是否是8.0,需要处理未知应用来源权限问题,否则直接安装
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        boolean hasInstallPermission = IntentUtils.isHasInstallPermissionWithO(getContext());
                        if (!hasInstallPermission) {
                            startInstallPermissionSettingActivity(getContext());
                        } else {
                            IntentUtils.openFile(getContext(), file);
                        }
                    } else {
                        IntentUtils.openFile(getContext(), file);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.d("test", "异常了=======");
                }
            }

            @Override
            public void progress(long progress, long total) {
                progressBar.setProgress((int) (progress * 1.0f / total * 100));
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("异常：" + e.getMessage());
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //可以安装apk
                if (file != null) {
                    IntentUtils.openFile(getContext(), file);
                }
            } else {
                showToast("安装权限失败哦，不能安装升级版apk");
            }
        }
    }


    @Override
    public UserCenterPresenter createPresenter() {
        return new UserCenterPresenter();
    }

    public static UserCenterFragment getInstance(String mTitle) {
        return new UserCenterFragment();
    }
}
