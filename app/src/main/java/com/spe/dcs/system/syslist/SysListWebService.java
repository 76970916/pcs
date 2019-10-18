package com.spe.dcs.system.syslist;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:列表
 * Author.
 * Data:
 */
public interface SysListWebService {

    @POST("/sysList/findAll")
    LiveData<Resource<List<SysListEntity>>> list();

}
