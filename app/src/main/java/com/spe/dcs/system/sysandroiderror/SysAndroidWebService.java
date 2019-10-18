package com.spe.dcs.system.sysandroiderror;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.RespEntity;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Desc:错误日志
 * Author.
 * Data:${DATA}
 */

public interface SysAndroidWebService {
    @Multipart
    @POST("/sysAndroidError/save")
    LiveData<Resource<RespEntity>> uploadLog(@Part MultipartBody.Part parts);
}
