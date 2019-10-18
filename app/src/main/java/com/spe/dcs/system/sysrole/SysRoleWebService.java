package com.spe.dcs.system.sysrole;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Desc:角色
 * Author.
 * Data:${DATA}
 */

public interface SysRoleWebService {
    @POST("/sysRole/findAll")
    LiveData<Resource<List<SysRoleEntity>>> list();

    @POST("/sysRole/findRoleListByUserId")
    LiveData<Resource<List<SysRoleEntity>>> findRoleListByUserId(@Query("userId") String id);

    @POST("/sysRole/findDataByCode")
    LiveData<Resource<SysRoleEntity>> findDataByCode(@Query("code") String code);
}
