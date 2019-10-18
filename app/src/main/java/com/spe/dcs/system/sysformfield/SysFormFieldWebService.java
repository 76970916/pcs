package com.spe.dcs.system.sysformfield;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:表单表
 * Author.
 * Data:${DATA}
 */

public interface SysFormFieldWebService {
    @POST("/sysFormField/findAll")
    LiveData<Resource<List<SysFormFieldEntity>>> list();

}
