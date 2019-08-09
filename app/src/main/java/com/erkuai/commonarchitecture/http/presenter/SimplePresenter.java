package com.erkuai.commonarchitecture.http.presenter;

import com.erkuai.commonarchitecture.http.RxPresenter;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/8/9.
 */

public class SimplePresenter extends RxPresenter<SimpleContract.SimpleView> implements SimpleContract.Presenter {

    @Inject
    public SimplePresenter() {
    }
}
