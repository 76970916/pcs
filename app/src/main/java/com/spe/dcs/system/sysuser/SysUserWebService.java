package com.spe.dcs.system.sysuser;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:用户
 * Author.
 * Data:
 */
public interface SysUserWebService {
    @POST("/sysUser/findAllData")
    LiveData<Resource<List<SysUserEntity>>> list();

}
