package com.spe.dcs.login;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.RespEntity;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginWebService {
    @POST("/login")
    LiveData<Resource<RespEntity>> login(@Query("username") String username, @Query("password") String password);

    @POST("/sysandroidupdate/findNewestVersion")
    LiveData<Resource<RespEntity>> updateUsers();
}
