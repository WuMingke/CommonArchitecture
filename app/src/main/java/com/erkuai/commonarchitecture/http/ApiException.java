package com.erkuai.commonarchitecture.http;

/**
 * Created by Administrator on 2019/8/9.
 */

public class ApiException extends RuntimeException {

    private int code;
    private Object content;

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
