package com.spe.dcs.system.sysres;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface SysResWebService {
    @POST("/sysRes/findAll")
    LiveData<Resource<List<SysResEntity>>> list();

}
