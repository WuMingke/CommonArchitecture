package com.erkuai.commonarchitecture.ui;

import android.os.Bundle;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseActivity;
import com.erkuai.commonarchitecture.http.contract.MainContract;
import com.erkuai.commonarchitecture.http.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {


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

    }
}
