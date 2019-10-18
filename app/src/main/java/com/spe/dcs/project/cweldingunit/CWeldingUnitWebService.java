package com.spe.dcs.project.cweldingunit;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:施工机组
 * Author.
 * Data:${DATA}
 */

public interface CWeldingUnitWebService {
    @POST("/cweldingunit/findAll")
    LiveData<Resource<List<CWeldingUnitEntity>>> list();
}
