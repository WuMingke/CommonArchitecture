package com.erkuai.commonarchitecture.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseActivity;
import com.erkuai.commonarchitecture.bean.JokeInfo;
import com.erkuai.commonarchitecture.http.contract.MainContract;
import com.erkuai.commonarchitecture.http.presenter.MainPresenter;
import com.erkuai.commonarchitecture.widgets.adapters.JokeInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.book_info_recycler)
    RecyclerView book_info_recycler;

    private JokeInfoAdapter jokeInfoAdapter;

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

        book_info_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        jokeInfoAdapter = new JokeInfoAdapter(new ArrayList<JokeInfo>());
        book_info_recycler.setAdapter(jokeInfoAdapter);


        // https://api.apiopen.top/getJoke?page=1&count=2&type=video
        mPresenter.getJokeInfo(1, 10, "video");
    }

    @Override
    public void getJokeInfoSuccess(List<JokeInfo> jokeInfoList) {


        jokeInfoAdapter.setNewData(jokeInfoList);

        showToast("getJokeInfoSuccess");
    }

    @Override
    public void getBookInfoFailed() {
        showToast("getBookInfoFailed");
    }
}
