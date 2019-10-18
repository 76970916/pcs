package com.spe.dcs.system.syslistfield;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:列表字段
 * Author.
 * Data:${DATA}
 */

public interface SysListFieldWebService {
    @POST("/sysListField/findAll")
    LiveData<Resource<List<SysListFieldEntity>>> list();
}
