package com.erkuai.commonarchitecture.http;

import com.erkuai.commonarchitecture.base.BaseApplication;
import com.erkuai.commonarchitecture.constants.StringConstants;
import com.erkuai.commonarchitecture.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.internal.$Gson$Types;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2019/4/16.
 */

public class BaseSubscriber<T> implements Observer<Object> {

    private final Type mType;

    public BaseSubscriber() {
        this.mType = getSuperclassTypeParameter(getClass());
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    @Override
    public void onSubscribe(Disposable d) {
        onStart(d);
    }

    @Override
    public void onNext(final Object o) {
        if (null == o || "null".equals(o)) {
            handleResponseNext(null);
        } else {
            Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                    try {
                        Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

                            @Override
                            public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                                if (src == src.longValue())
                                    return new JsonPrimitive(src.longValue());
                                return new JsonPrimitive(src);
                            }
                        }).create();
                        emitter.onNext((T) gson.fromJson(gson.toJson(o), mType));
                        emitter.onComplete();
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                }
            }).compose(Utils.<T>rx2SchedulerHelperO())
                    .subscribe(new Consumer<T>() {
                        @Override
                        public void accept(T t) throws Exception {
                            handleResponseNext(t);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            handleResponseFailed(throwable);
                        }
                    });
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException
                || e instanceof ConnectTimeoutException
                || e instanceof ConnectException) {
            handleResponseFailed(e);
            Utils.showToast(BaseApplication.mContext, "网络连接异常");
        } else if (e instanceof ApiException) {
            switch (((ApiException) e).getCode()) {
                case StringConstants.STATUS_XXX:
                    handleResponseFailed(e);
                    break;
                default:
                    handleResponseFailed(e);
                    break;
            }
        } else {
            handleResponseFailed(e);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    public void onStart(Disposable disposable) {
    }

    public void onFinish() {
    }

    public void handleResponseNext(T t) {
    }

    public void handleResponseFailed(Throwable e) {
        // Utils.showToast(BaseApplication.mContext,e.toString());
    }
}
