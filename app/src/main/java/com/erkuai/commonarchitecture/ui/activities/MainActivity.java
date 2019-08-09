package com.erkuai.commonarchitecture.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseActivity;
import com.erkuai.commonarchitecture.bean.BookInfo;
import com.erkuai.commonarchitecture.http.contract.MainContract;
import com.erkuai.commonarchitecture.http.presenter.MainPresenter;
import com.erkuai.commonarchitecture.widgets.adapters.BookInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.book_info_recycler)
    RecyclerView book_info_recycler;

    private BookInfoAdapter bookInfoAdapter;

    @Override
    protected void initInject(Bundle bundle) {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

        book_info_recycler.setLayoutManager(new LinearLayoutManager(this));
        bookInfoAdapter = new BookInfoAdapter(new ArrayList<BookInfo.DataBean>());
        book_info_recycler.setAdapter(bookInfoAdapter);

        mPresenter.getBookInfo("盗墓笔记");
    }

    @Override
    public void getBookInfoSuccess(List<BookInfo.DataBean> dataBean) {
        Log.i("wmk", dataBean.get(0).getAuthor());
        bookInfoAdapter.setNewData(dataBean);
    }

    @Override
    public void getBookInfoFailed() {
        showToast("getBookInfoFailed");
    }
}
