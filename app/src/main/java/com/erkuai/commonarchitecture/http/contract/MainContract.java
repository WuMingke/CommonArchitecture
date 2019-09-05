package com.erkuai.commonarchitecture.http.contract;

import com.erkuai.commonarchitecture.base.BaseView;
import com.erkuai.commonarchitecture.bean.JokeInfo;
import com.erkuai.commonarchitecture.http.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2019/8/9.
 */

public interface MainContract {

    interface MainView extends BaseView {
        void getJokeInfoSuccess(List<JokeInfo> jokeInfoList);

        void getBookInfoFailed();
    }

    interface Presenter extends BasePresenter<MainView> {
        void getJokeInfo(int page, int count, String type);
    }
}
