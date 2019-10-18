package com.spe.dcs.system.sysorgres;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:组织资源
 * Author.
 * Data:${DATA}
 */

public class SysOrgResViewModel extends ViewModel {

    private static final String TAG = "SysOrgResViewModel";
    @Inject
    public SysOrgResResposity sysOrgResResposity;

    @Inject
    public SysOrgResViewModel() {
//        Log.d(TAG, "SysFormFieldViewModel被创建了");
    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysOrgResEntity>>> list(boolean local) {
        return sysOrgResResposity.list(local);

    }

}
