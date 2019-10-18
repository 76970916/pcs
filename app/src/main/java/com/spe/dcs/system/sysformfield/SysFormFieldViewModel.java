package com.spe.dcs.system.sysformfield;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:表单表
 * Author.
 * Data:${DATA}
 */

public class SysFormFieldViewModel extends ViewModel {
    private static final String TAG = "SysFormFieldViewModel";
    @Inject
    public SysFormFieldResposity sysFormFieldResposity;

    @Inject
    public SysFormFieldViewModel() {
//        Log.d(TAG, "SysFormFieldViewModel被创建了");
    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysFormFieldEntity>>> list(boolean local) {
        return sysFormFieldResposity.list(local);

    }

}
