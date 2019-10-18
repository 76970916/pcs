package com.spe.dcs.system.sysorgres;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:组织资源
 * Author.
 * Data:${DATA}
 */

public interface SysOrgResWebService {
    @POST("/sysOrgRes/findAll")
    LiveData<Resource<List<SysOrgResEntity>>> list();
}
