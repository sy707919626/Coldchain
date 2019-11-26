package com.bfby.coldchain.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */

public class BaseBeans<T> implements Serializable {
    private int Code;
    private List<T> Data;
    private String Message;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
