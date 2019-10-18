package com.spe.dcs.system.sysres;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:资源
 * Author.
 * Data:${DATA}
 */

public class SysResViewModel extends ViewModel {

    private static final String TAG = "SysResViewModel";
    @Inject
    public SysResResposity sysResResposity;

    @Inject
    public SysResViewModel() {

    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysResEntity>>> list(boolean local) {
        return sysResResposity.list(local);

    }


}
