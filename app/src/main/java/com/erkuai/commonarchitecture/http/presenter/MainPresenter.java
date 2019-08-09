package com.erkuai.commonarchitecture.http.presenter;

import android.util.Log;

import com.erkuai.commonarchitecture.bean.BookInfo;
import com.erkuai.commonarchitecture.http.BaseSubscriber;
import com.erkuai.commonarchitecture.http.RetrofitHelper;
import com.erkuai.commonarchitecture.http.RxPresenter;
import com.erkuai.commonarchitecture.http.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2019/8/9.
 */

public class MainPresenter extends RxPresenter<MainContract.MainView> implements MainContract.Presenter {

    private static final String MAIN_REQUEST_ERROR_TAG = "MAIN_REQUEST_ERROR_TAG";

    @Inject
    public MainPresenter() {
    }

    @Override
    public void getBookInfo(String bookName) {
        RetrofitHelper.getRetrofitHelper().getBookInfo(bookName, new BaseSubscriber<BookInfo>() {
            @Override
            public void onStart(Disposable disposable) {
                super.onStart(disposable);
                addDisposable(disposable);
            }

            @Override
            public void handleResponseNext(BookInfo bookInfo) {
                super.handleResponseNext(bookInfo);
                mView.getBookInfoSuccess(bookInfo.getData());
            }

            @Override
            public void handleResponseFailed(Throwable e) {
                super.handleResponseFailed(e);
                mView.getBookInfoFailed();
                Log.i(MAIN_REQUEST_ERROR_TAG, MAIN_REQUEST_ERROR_TAG + "----" + e.toString());
            }
        });
    }
}
