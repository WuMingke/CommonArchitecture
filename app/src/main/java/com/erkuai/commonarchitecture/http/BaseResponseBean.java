package com.erkuai.commonarchitecture.http;

/**
 * Created by Administrator on 2019/8/9.
 */

public class BaseResponseBean {

    private int code;

    private String message;

    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public Object getResponse() {
        return result;
    }

    public void setResponse(Object response) {
        this.result = response;
    }
}
