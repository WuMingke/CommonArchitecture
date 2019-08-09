package com.erkuai.commonarchitecture.ui;

import android.os.Bundle;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseFragment;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;
import com.erkuai.commonarchitecture.http.presenter.SimplePresenter;

/**
 * Created by Administrator on 2019/8/9.
 */

public class AFragment extends BaseFragment<SimplePresenter> implements SimpleContract.SimpleView {



    @Override
    protected void initInject(Bundle bundle) {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_a;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

    }
}
