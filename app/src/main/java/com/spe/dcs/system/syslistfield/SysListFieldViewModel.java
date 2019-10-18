package com.spe.dcs.system.syslistfield;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:列表字段
 * Author.
 * Data:${DATA}
 */

public class SysListFieldViewModel extends ViewModel {

    private static final String TAG = "SysFormFieldTypeViewModel";
    @Inject
    public SysListFieldResposity sysListFieldResposity;

    @Inject
    public SysListFieldViewModel() {
//        Log.d(TAG, "SysFormFieldViewModel被创建了");
    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysListFieldEntity>>> list(boolean local) {
        return sysListFieldResposity.list(local);

    }
}
