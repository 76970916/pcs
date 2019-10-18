package com.spe.dcs.system.sysres;

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
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class SysResResposity {

    private final SysResDao sysResDao;
    private final SysResWebService sysResWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysResResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysResWebService = retrofit.create(SysResWebService.class);
        this.sysResDao = sysDatabase.sysResDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysResEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysResEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysResEntity> item) {
                    sysResDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysResEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysResEntity> loadFromDb() {
                return sysResDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysResEntity>>> createCall() {
                return sysResWebService.list();
            }
        }.getAsLiveData();
    }

}
