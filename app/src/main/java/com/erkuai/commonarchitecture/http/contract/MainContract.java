package com.erkuai.commonarchitecture.http.contract;

import com.erkuai.commonarchitecture.base.BaseView;
import com.erkuai.commonarchitecture.bean.BookInfo;
import com.erkuai.commonarchitecture.http.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2019/8/9.
 */

public interface MainContract {

    interface MainView extends BaseView {
        void getBookInfoSuccess(List<BookInfo.DataBean> dataBeanList);

        void getBookInfoFailed();
    }

    interface Presenter extends BasePresenter<MainView> {
        void getBookInfo(String bookName);
    }
}
