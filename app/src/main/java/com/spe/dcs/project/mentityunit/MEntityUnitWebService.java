package com.spe.dcs.project.mentityunit;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Desc:单元
 * Author.
 * Data:${DATA}
 */

public interface MEntityUnitWebService {
    @POST("/mentityunit/findAll")
    LiveData<Resource<List<MEntityUnitEntity>>> list();


    @POST("/mentityunit/findAllByOneProjectNum")
    LiveData<Resource<List<MEntityUnitEntity>>> lists(@Query("oneProjectNum") String piplineNumber);

}
