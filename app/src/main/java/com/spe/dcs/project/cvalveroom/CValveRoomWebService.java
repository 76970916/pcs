package com.spe.dcs.project.cvalveroom;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface CValveRoomWebService {
    @POST("/cvalveroom/findAll")
    LiveData<Resource<List<CValveRoomEntity>>> list();
}
