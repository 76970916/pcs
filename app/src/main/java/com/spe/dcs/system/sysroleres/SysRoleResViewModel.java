package com.spe.dcs.system.sysroleres;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:角色资源
 * Author.
 * Data:${DATA}
 */

public class SysRoleResViewModel extends ViewModel {

    private static final String TAG = "SysRoleResViewModel";
    @Inject
    public SysRoleResResposity sysRoleResposity;

    @Inject
    public SysRoleResViewModel() {

    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysRoleResEntity>>> list(boolean local) {
        return sysRoleResposity.list(local);

    }

}
