package com.bfby.coldchain.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/27.
 */

public class BaseBean<T> implements Serializable {
    private int Code;
    private T Data;
    private String Message;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
