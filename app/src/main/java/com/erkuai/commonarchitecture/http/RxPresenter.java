package com.erkuai.commonarchitecture.http;

import com.erkuai.commonarchitecture.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2019/8/9.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    protected CompositeDisposable compositeDisposable;

    protected void addDisposable(Disposable subscription) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
