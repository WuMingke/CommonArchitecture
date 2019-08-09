package com.erkuai.commonarchitecture.http.presenter;

import com.erkuai.commonarchitecture.http.RxPresenter;
import com.erkuai.commonarchitecture.http.contract.MainContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/8/9.
 */

public class MainPresenter extends RxPresenter<MainContract.MainView> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {
    }
}
