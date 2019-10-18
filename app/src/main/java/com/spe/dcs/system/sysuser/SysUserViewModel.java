package com.spe.dcs.system.sysuser;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:用户
 * Author.
 * Data:
 */
public class SysUserViewModel extends ViewModel {
    private static final String TAG = "SysUser";
    @Inject
    public SysUserRepository sysUserRepository;

    @Inject
    public SysUserViewModel() {
        Log.d(TAG, "SysUserViewModel被创建了");
    }


    public LiveData<Resource<List<SysUserEntity>>> list(boolean local) {
        return sysUserRepository.list(local);

    }

}
