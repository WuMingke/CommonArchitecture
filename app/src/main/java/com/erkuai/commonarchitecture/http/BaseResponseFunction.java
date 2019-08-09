package com.erkuai.commonarchitecture.http;

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
        String msg = baseResponseBean.getMsg();
        ApiException apiException = new ApiException(msg, code);
        switch (code) {
            case StringConstants.STATUS_SUCCESS:
                if (((LinkedTreeMap) baseResponseBean.getResponse()).get("data") == null) {
                    return "null";
                }
                return ((LinkedTreeMap) baseResponseBean.getResponse()).get("data");
            default:
                apiException.setContent(((LinkedTreeMap) baseResponseBean.getResponse()));
                throw apiException;


        }
    }
}
