package com.erkuai.commonarchitecture.http;

import android.annotation.SuppressLint;
import android.util.Log;

import com.erkuai.commonarchitecture.constants.StringConstants;
import com.erkuai.commonarchitecture.utils.Utils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/8/9.
 */

public class RetrofitHelper {

    /****************************************请求******************************************/
    public void getJokeInfo(int page, int count, String type, BaseSubscriber<?> subscriber) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("count", String.valueOf(count));
        map.put("type", type);
        getRequestBaseBean(getApi().getJokeInfo(map), subscriber);
    }

    /****************************************配置******************************************/
    private OkHttpClient okHttpClient;
    private Api api;
    private static RetrofitHelper retrofitHelper;

    private RetrofitHelper() {
        okHttpClient = getOkHttpClient();
        api = getApi();
    }

    public static RetrofitHelper getRetrofitHelper() {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor retrofitLog = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        retrofitLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.connectTimeout(StringConstants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(StringConstants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(StringConstants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(retrofitLog);
        /*builder.sslSocketFactory(createSSLSocketFactory());//HTTPS配置
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });*/
        return builder.build();
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }

    private class TrustAllCerts implements X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


    public Api getApi() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(StringConstants.HOST)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api.class);
        }
        return api;
    }

    private void getRequestBaseBean(Observable<BaseResponseBean> observable, BaseSubscriber<?> subscriber) {
        observable.map(new BaseResponseFunction()).compose(Utils.rx2SchedulerHelperO()).subscribe(subscriber);
    }
}
