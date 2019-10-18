package com.spe.dcs.system.sysroleuser;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Desc:角色用户
 * Author.
 * Data:${DATA}
 */

public class SysRoleUserResposity {
    private final SysRoleUserDao sysRoleUserDao;
    private final SysRoleUserWebService sysRoleUserWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysRoleUserResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysRoleUserWebService = retrofit.create(SysRoleUserWebService.class);
        this.sysRoleUserDao = sysDatabase.sysRoleUserDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysRoleUserEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysRoleUserEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysRoleUserEntity> item) {
                    sysRoleUserDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysRoleUserEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysRoleUserEntity> loadFromDb() {
                return sysRoleUserDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysRoleUserEntity>>> createCall() {
                return sysRoleUserWebService.list();
            }
        }.getAsLiveData();
    }


}

