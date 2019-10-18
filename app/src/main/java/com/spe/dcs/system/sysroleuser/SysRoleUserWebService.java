package com.spe.dcs.system.sysroleuser;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:角色用户
 * Author.
 * Data:${DATA}
 */

public interface SysRoleUserWebService {
    //    @POST("/sysRoleUser/findAll")
    @POST("/sysUser/findRoleUserAllData")
    LiveData<Resource<List<SysRoleUserEntity>>> list();
}
