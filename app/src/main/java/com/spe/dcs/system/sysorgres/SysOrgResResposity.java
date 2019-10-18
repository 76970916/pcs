package com.spe.dcs.system.sysorgres;

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
 * Desc:组织资源
 * Author.
 * Data:${DATA}
 */

public class SysOrgResResposity {
    private final SysOrgResDao sysOrgResDao;
    private final SysOrgResWebService sysOrgResWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysOrgResResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysOrgResWebService = retrofit.create(SysOrgResWebService.class);
        this.sysOrgResDao = sysDatabase.sysOrgResDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysOrgResEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysOrgResEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysOrgResEntity> item) {
                sysOrgResDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysOrgResEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysOrgResEntity> loadFromDb() {
                return sysOrgResDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysOrgResEntity>>> createCall() {
                return sysOrgResWebService.list();
            }
        }.getAsLiveData();
    }
}
