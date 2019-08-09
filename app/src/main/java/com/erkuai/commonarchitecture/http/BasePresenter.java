package com.erkuai.commonarchitecture.http;

import com.erkuai.commonarchitecture.base.BaseView;

/**
 * Created by Administrator on 2019/8/9.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
