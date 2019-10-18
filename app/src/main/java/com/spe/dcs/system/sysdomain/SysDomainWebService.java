package com.spe.dcs.system.sysdomain;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:域表
 * Author.
 * Data:
 */
public interface SysDomainWebService {

    @POST("/sysDomain/findAllData")
    LiveData<Resource<List<SysDomainEntity>>> list();

}
