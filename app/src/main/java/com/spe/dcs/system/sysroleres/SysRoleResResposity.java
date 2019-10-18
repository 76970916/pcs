package com.spe.dcs.system.sysroleres;

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
 * Desc:角色资源
 * Author.
 * Data:${DATA}
 */

public class SysRoleResResposity {

    private final SysRoleResDao sysRoleResDao;
    private final SysRoleResWebService sysRoleResWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysRoleResResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysRoleResWebService = retrofit.create(SysRoleResWebService.class);
        this.sysRoleResDao = sysDatabase.sysRoleResDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysRoleResEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysRoleResEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysRoleResEntity> item) {
                    sysRoleResDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysRoleResEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysRoleResEntity> loadFromDb() {
                return sysRoleResDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysRoleResEntity>>> createCall() {
                return sysRoleResWebService.list();
            }
        }.getAsLiveData();
    }


}
