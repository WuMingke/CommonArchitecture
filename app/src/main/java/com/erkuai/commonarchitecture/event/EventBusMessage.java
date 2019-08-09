package com.erkuai.commonarchitecture.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/9.
 */

public class EventBusMessage<T> {

    private T t;

    private Map<String, Object> extraData = new HashMap<>();

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    public void putMoreData(String key, Object o) {
        if (extraData == null) {
            extraData = new HashMap<>();
        }
        extraData.put(key, o);
    }

    public Object getMoreData(String key) {
        return extraData == null ? null : extraData.get(key);
    }
}
