package com.spe.dcs.app;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class Response<T> {
    public int total;
    private T rows;

    public T setData(T rows) {
        return this.rows = rows;
    }

    public void setResp(Response<T> resp) {
        resp.setData(rows);
    }

    public T getData() {
        return rows;
    }
}
