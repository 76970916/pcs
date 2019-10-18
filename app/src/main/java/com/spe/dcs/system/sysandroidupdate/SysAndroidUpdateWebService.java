package com.spe.dcs.system.sysandroidupdate;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;
import com.spe.dcs.common.NewVersionEntity;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Desc:APP更新
 * Author.
 * Data:${DATA}
 */

public interface SysAndroidUpdateWebService {

    @GET("/sysAndroidUpdate/downNewestApk")
    LiveData<Resource<ResponseBody>> upgrade();


    @POST("/sysAndroidUpdate/findNewestVersion")
    LiveData<Resource<NewVersionEntity>> findNewVersion();
}
