package com.spe.dcs.system.sysformfieldtype;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;

import java.util.List;

import retrofit2.http.POST;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public interface SysFormFieldTypeWebService {
    @POST("/sysFormFieldType/findAll")
    LiveData<Resource<List<SysFormFieldTypeEntity>>> list();
}
