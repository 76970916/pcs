package com.spe.dcs.app.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class NetIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("Connection", "close").build();
        return chain.proceed(request);

    }
}
