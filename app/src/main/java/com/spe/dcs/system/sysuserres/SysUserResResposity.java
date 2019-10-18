package com.spe.dcs.system.sysuserres;

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

public class SysUserResResposity {
    private final SysUserResDao sysUserResDao;
    private final SysUserResWebService sysUserResWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysUserResResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysUserResWebService = retrofit.create(SysUserResWebService.class);
        this.sysUserResDao = sysDatabase.sysUserResDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysUserResEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysUserResEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysUserResEntity> item) {
                    sysUserResDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysUserResEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysUserResEntity> loadFromDb() {
                return sysUserResDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysUserResEntity>>> createCall() {
                return sysUserResWebService.list();
            }
        }.getAsLiveData();
    }


}
