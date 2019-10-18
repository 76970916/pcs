package com.spe.dcs.project.mprojectinfo;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:59.1项目
 * Author.
 * Data:
 */
public interface MProjectInfoWebService {

    @POST("/mprojectinfo/findList")
    LiveData<Resource<List<MProjectInfoEntity>>> list();

}
