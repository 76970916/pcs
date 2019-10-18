package com.spe.dcs.project.mcontractorinfo;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Desc:59.3施工标段
 * Author.
 * Data:
 */
public interface MContractorInfoWebService {

    @POST("/mcontractorinfo/findAllSectionNum")
    LiveData<Resource<List<MContractorInfoEntity>>> list();

    @POST("/mcontractorinfo/findAllSupervisionUnit")
    LiveData<Resource<List<MContractorInfoEntity>>> listSupervisior();


    @POST("/mcontractorinfo/findAllBySectionNum")
    LiveData<Resource<List<MContractorInfoEntity>>> lists(@Query("projectNum") String piplineNumber);

}
