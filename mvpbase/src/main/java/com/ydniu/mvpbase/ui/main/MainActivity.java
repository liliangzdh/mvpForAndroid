package com.ydniu.mvpbase.ui.main;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseMvpActivity;
import com.ydniu.mvpbase.bean.TabEntity;
import com.ydniu.mvpbase.ui.main.find.FindFragment;
import com.ydniu.mvpbase.ui.main.home.HomeFragment;
import com.ydniu.mvpbase.ui.main.userCenter.UserCenterFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;


    // 顶部滑动的标签栏
    private String[] mTitles = {"首页", "发现", "我的"};
    // 未被选中的图标
    private int[] mIconUnSelectIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    // 被选中的图标
    private int[] mIconSelectIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private UserCenterFragment mUserCenterFragment;

    private int mCurrIndex = 0;

    @Override
    public MainPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void getIntentData(Intent intent) {

    }


    @Override
    public void initViews() {
        initTab();
        switchFragment(0);
    }

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private void initTab() {

        mTabEntities.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    /**
     * 切换Fragment
     *
     * @param position 下标
     */
    private void switchFragment(int position) {
        // Fragment事务管理器
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
//        LogUtils.e("current position tab" + position);
        switch (position) {
            case 0: //首页
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(R.id.fl_container, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1: //视频
                if (mFindFragment == null) {
                    mFindFragment = FindFragment.getInstance(mTitles[1]);
                    transaction.add(R.id.fl_container, mFindFragment, "video");
                } else {
                    transaction.show(mFindFragment);
                }
                break;

            case 2: //更多
                if (mUserCenterFragment == null) {
                    mUserCenterFragment = UserCenterFragment.getInstance(mTitles[2]);
                    transaction.add(R.id.fl_container, mUserCenterFragment, "mine");
                } else {
                    transaction.show(mUserCenterFragment);
                }
                break;
            default:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(R.id.fl_container, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
        }
        mCurrIndex = position;
        tabLayout.setCurrentTab(mCurrIndex);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mFindFragment) {
            transaction.hide(mFindFragment);
        }
        if (null != mUserCenterFragment) {
            transaction.hide(mUserCenterFragment);
        }
    }

    @Override
    public void initData() {

    }


    @Override
    public void showData(String str) {


    }

}