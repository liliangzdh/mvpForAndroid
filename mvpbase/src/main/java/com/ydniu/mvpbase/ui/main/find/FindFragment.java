package com.ydniu.mvpbase.ui.main.find;

import android.widget.TextView;

import com.ydniu.mvpbase.R;
import com.ydniu.mvpbase.base.BaseFragment;
import com.ydniu.mvpbase.event.TestEvent;
import com.ydniu.mvpbase.rx.RxBus;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.FindView {


    @BindView(R.id.tv)
    TextView textView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initData() {
        presenter.addDispose(
                RxBus.getDefault()
                        .toObservable(TestEvent.class)
                        .subscribe(new Consumer<TestEvent>() {
                            @Override
                            public void accept(TestEvent testEvent) {
                                if (testEvent != null) {
                                    textView.setText(testEvent.getName() + ":" + testEvent.getId());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {

                            }
                        }));

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
