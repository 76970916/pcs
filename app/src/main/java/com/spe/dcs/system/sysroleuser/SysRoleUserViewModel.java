package com.spe.dcs.system.sysroleuser;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:角色用户
 * Author.
 * Data:${DATA}
 */

public class SysRoleUserViewModel extends ViewModel {
    private static final String TAG = "SysRoleUserViewModel";
    @Inject
    public SysRoleUserResposity sysRoleUserResposity;

    @Inject
    public SysRoleUserViewModel() {

    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysRoleUserEntity>>> list(boolean local) {
        return sysRoleUserResposity.list(local);

    }
}
