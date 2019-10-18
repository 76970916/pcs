package com.spe.dcs.app.net;


public class RespEntity {
    private int code;
    private String msg;
    private Object data;

    public RespEntity(int coder, String msg, Object data) {
        this.code = coder;
        this.msg = msg;
        this.data = data;
    }

    public RespEntity(int coder, String msg) {
        this.code = coder;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = this.code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ",\"msg\":\"" + msg + "\"}";
    }
}
