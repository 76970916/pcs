package com.spe.dcs.app.net;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class FlagRespEntity {
    private boolean flag;
    private String msg;


    public FlagRespEntity(boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public FlagRespEntity(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
