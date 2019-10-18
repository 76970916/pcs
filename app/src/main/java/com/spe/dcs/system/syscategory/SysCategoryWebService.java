package com.spe.dcs.system.syscategory;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:分类表
 * Author.
 * Data:
 */
public interface SysCategoryWebService {
    @POST("/sysCategory/findAllData")
    LiveData<Resource<List<SysCategoryEntity>>> list();

}
