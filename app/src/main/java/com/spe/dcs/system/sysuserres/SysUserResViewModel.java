package com.spe.dcs.system.sysuserres;

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

public class SysUserResViewModel extends ViewModel {
    private static final String TAG = "SysUserResViewModel";
    @Inject
    public SysUserResResposity sysUserResResposity;

    @Inject
    public SysUserResViewModel() {

    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysUserResEntity>>> list(boolean local) {
        return sysUserResResposity.list(local);

    }

}
