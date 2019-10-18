package com.spe.dcs.common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Desc:下载回调接口
 * Author.
 * Data:${DATA}
 */

public abstract class RetrofitCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onFailure(call, new Throwable(response.message()));
        }
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public void onLoading(long total, long progress) {
    }
}