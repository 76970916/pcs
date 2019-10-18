package com.spe.dcs.project.mentityfuction;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Desc:功能区
 * Author.
 * Data:
 */
public interface MEntityFuctionWebService {

    @POST("/mentityfuction/findAll")
    LiveData<Resource<List<MEntityFuctionEntity>>> list();

    @POST("/mentityfuction/findAllByProUnitNum")
    LiveData<Resource<List<MEntityFuctionEntity>>> lists(@Query("proUnitNum") String piplineNumber);

}
