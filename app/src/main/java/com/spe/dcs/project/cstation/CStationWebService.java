package com.spe.dcs.project.cstation;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface CStationWebService {
    @POST("/cstation/findAll")
    LiveData<Resource<List<CStationEntity>>> list();

}
