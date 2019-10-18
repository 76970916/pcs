package com.spe.dcs.app.net;


import android.app.Application;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spe.dcs.BuildConfig;
import com.spe.dcs.app.PcsSharedPreferences;
import com.spe.dcs.app.net.livedatacalladapter.LiveDataCallAdapterFactory;
import com.spe.dcs.app.net.livedatacalladapter.LiveDataResponseBodyConverterFactory;
import com.spe.dcs.utility.EntityUtils;
import com.spe.dcs.utility.StringConverterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by
 * DATE 2018/11/21
 */
@Module
public class NetModule {

    // Constructor needs one parameter to instantiate.
    public NetModule() {
    }

    @Singleton
    @Provides
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
        //.addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.i("HTTP", message));
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new NetIntercepter())
                .addInterceptor(new ReceivedCookiesInterceptor()) //这部分
                .addInterceptor(new AddCookiesInterceptor()) //这部分
                .build();
        return okhttpClient;
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okhttpClient, PcsSharedPreferences pcsSharedPreferences) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(pcsSharedPreferences.getBaseUrl())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
