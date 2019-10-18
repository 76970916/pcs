package com.spe.dcs.system.sysfield;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:字段表
 * Author.
 * Data:
 */
public interface SysFieldWebService {

    @POST("/sysTableField/findAll")
    LiveData<Resource<List<SysFieldEntity>>> list();

}
