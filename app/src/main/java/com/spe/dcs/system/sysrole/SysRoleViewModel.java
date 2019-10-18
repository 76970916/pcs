package com.spe.dcs.system.sysrole;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:角色
 * Author.
 * Data:${DATA}
 */

public class SysRoleViewModel extends ViewModel {
    private static final String TAG = "SysRoleViewModel";
    @Inject
    public SysRoleResposity sysRoleResposity;

    @Inject
    public SysRoleViewModel() {

    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysRoleEntity>>> list(boolean local) {
        return sysRoleResposity.list(local);

    }

    public LiveData<Resource<List<SysRoleEntity>>> findRoleListByUserId(String id) {
        return sysRoleResposity.findRoleListByUserId(id);
    }

    public LiveData<Resource<SysRoleEntity>> findDataByCode(String code) {
        return sysRoleResposity.findDataByCode(code);
    }
}
