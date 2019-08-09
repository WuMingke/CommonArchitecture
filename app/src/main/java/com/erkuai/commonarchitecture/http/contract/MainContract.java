package com.erkuai.commonarchitecture.http.contract;

import com.erkuai.commonarchitecture.base.BaseView;
import com.erkuai.commonarchitecture.http.BasePresenter;

/**
 * Created by Administrator on 2019/8/9.
 */

public interface MainContract {

    interface MainView extends BaseView {

    }

    interface Presenter extends BasePresenter<MainView> {

    }
}
