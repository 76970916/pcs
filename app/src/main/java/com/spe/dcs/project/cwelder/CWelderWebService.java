package com.spe.dcs.project.cwelder;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:施工焊工数据
 * Author.
 * Data:${DATA}
 */

public interface CWelderWebService {

    @POST("/cwelder/findAll")
    LiveData<Resource<List<CWelderEntity>>> list();
}
