package com.spe.dcs.system.sysdomain;

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
 * Desc:域表
 * Author.
 * Data:
 */

public class SysDomainRepository {

    private final SysDomainDao sysdomainDao;
    private final SysDomainWebService sysdomainWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysDomainRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysdomainWebService = retrofit.create(SysDomainWebService.class);
        this.sysdomainDao = sysDatabase.sysDomainDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysDomainEntity>>> list(boolean local, String type) {
        return new NetworkBoundResource<List<SysDomainEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysDomainEntity> item) {
                sysdomainDao.save(item);

            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysDomainEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysDomainEntity> loadFromDb() {
                return sysdomainDao.loadList(type);
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysDomainEntity>>> createCall() {
                return sysdomainWebService.list();
            }
        }.

                getAsLiveData();
    }

}
