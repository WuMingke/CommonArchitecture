package com.erkuai.commonarchitecture.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseActivity;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;
import com.erkuai.commonarchitecture.http.presenter.SimplePresenter;

/**
 * Created by Administrator on 2019/8/9.
 */

public class TextActivity extends BaseActivity<SimplePresenter> implements SimpleContract.SimpleView {

    @Override
    protected void initInject(Bundle bundle) {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_text;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

    }


    public void jump(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
