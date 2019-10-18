package com.spe.dcs.system.sysandroidupdate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface SysAndroidUpdateDownLoadWebService {

    @POST("/sysAndroidUpdate/downNewestApk")
    Call<ResponseBody> download();

}
