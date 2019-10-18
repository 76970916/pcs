package com.spe.dcs.system.systable;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:表
 * Author.
 * Data:
 */
public interface SysTableWebService {

    @POST("/sysTable/findAll")
    LiveData<Resource<List<SysTableEntity>>> list();

}
