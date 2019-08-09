package com.erkuai.commonarchitecture.http.contract;

import com.erkuai.commonarchitecture.base.BaseView;
import com.erkuai.commonarchitecture.http.BasePresenter;

/**
 * Created by Administrator on 2019/8/9.
 * <p>
 * 没有网络请求的界面
 */

public interface SimpleContract {

    interface SimpleView extends BaseView {

    }

    interface Presenter extends BasePresenter<SimpleView> {

    }
}
