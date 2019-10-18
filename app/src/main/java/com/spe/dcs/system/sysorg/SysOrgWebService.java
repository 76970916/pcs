package com.spe.dcs.system.sysorg;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface SysOrgWebService {

    @POST("/sysOrg/findAllData")
    LiveData<Resource<List<SysOrgEntity>>> list();
    @GET("/sysOrg/findDateByCode")
    LiveData<Resource<SysOrgEntity>>findDataByCode(@Query("code") String code);
}
