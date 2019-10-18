package com.spe.dcs.system.syslistbutton;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:列表按钮
 * Author.
 * Data:${DATA}
 */

public interface SysListButtonWebService {
    @POST("/sysListButton/findAll")
    LiveData<Resource<List<SysListButtonEntity>>> list();
}
