package com.spe.dcs.system.sysroleres;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:角色资源
 * Author.
 * Data:${DATA}
 */

public interface SysRoleResWebService {
    @POST("/sysRoleRes/findAll")
    LiveData<Resource<List<SysRoleResEntity>>> list();
}
