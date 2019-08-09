package com.erkuai.commonarchitecture.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2019/8/9.
 */

public interface Api {

    @GET("novelInfoApi")
    Observable<BaseResponseBean> getBookInfo(@QueryMap Map<String, String> map);
}
