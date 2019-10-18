package com.spe.dcs.project.moneprojectinfo;

import android.arch.lifecycle.LiveData;

import retrofit2.http.Query;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:子项目
 * Author.
 * Data:
 */
public interface MOneProjectInfoWebService {

    @POST("/moneprojectinfo/findList")
    LiveData<Resource<List<MOneProjectInfoEntity>>> list();

    @POST("/moneprojectinfo/findOneProByProjectNum")
    LiveData<Resource<List<MOneProjectInfoEntity>>> lists(@Query("projectNum") String projectNumber);

}
