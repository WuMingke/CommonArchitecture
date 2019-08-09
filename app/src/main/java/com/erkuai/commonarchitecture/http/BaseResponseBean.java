package com.erkuai.commonarchitecture.http;

/**
 * Created by Administrator on 2019/8/9.
 */

public class BaseResponseBean {

    private int code;

    private String msg;

    private Object response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
