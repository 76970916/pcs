package com.spe.dcs.project.cweldingprocedure;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:焊接工艺规程
 * Author.
 * Data:${DATA}
 */

public interface CWeldingProcedureWebService {
    @POST("/cweldingprocedure/findAll")
    LiveData<Resource<List<CWeldingProcedureEntity>>> list();
}
