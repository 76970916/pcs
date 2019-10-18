package com.spe.dcs.system.sysformfieldtype;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class SysFormFieldTypeViewModel extends ViewModel {
    private static final String TAG = "SysFormFieldTypeViewModel";
    @Inject
    public SysFormFieldTypeResposity sysFormFieldTypeResposity;

    @Inject
    public SysFormFieldTypeViewModel() {
//        Log.d(TAG, "SysFormFieldViewModel被创建了");
    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysFormFieldTypeEntity>>> list(boolean local) {
        return sysFormFieldTypeResposity.list(local);

    }

}
