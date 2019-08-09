package com.erkuai.commonarchitecture.http;

import android.util.Log;

import com.erkuai.commonarchitecture.bean.BookInfo;
import com.erkuai.commonarchitecture.constants.StringConstants;
import com.google.gson.internal.LinkedTreeMap;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2019/8/9.
 */

public class BaseResponseFunction implements Function<BaseResponseBean, Object> {
    @Override
    public Object apply(BaseResponseBean baseResponseBean) throws Exception {
        int code = baseResponseBean.getCode();

        switch (code) {
            case StringConstants.STATUS_SUCCESS:
                if (baseResponseBean.getResponse() == null) {
                    return "null";
                }
                return baseResponseBean.getResponse();
            default:
                String msg = baseResponseBean.getMsg();
                ApiException apiException = new ApiException(msg, code);
                apiException.setContent(((LinkedTreeMap) baseResponseBean.getResponse()));
                throw apiException;

        }
    }
}
