package com.spe.dcs.system.sysuserres;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface SysUserResWebService {
    @POST("/sysUserRes/findAll")
    LiveData<Resource<List<SysUserResEntity>>> list();
}
