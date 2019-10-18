package com.spe.dcs.system.syscategory;

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
 * Desc:分类表
 * Author.
 * Data:
 */

public class SysCategoryRepository {

    private final SysCategoryDao syscategoryDao;
    private final SysCategoryWebService syscategoryWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysCategoryRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.syscategoryWebService = retrofit.create(SysCategoryWebService.class);
        this.syscategoryDao = sysDatabase.sysCategoryDao();
        this.sysDatabase = sysDatabase;
    }

    public LiveData<Resource<List<SysCategoryEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysCategoryEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysCategoryEntity> item) {
                syscategoryDao.save(item);
                List<SysCategoryEntity> sysCategoryEntities = syscategoryDao.loadList();

            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysCategoryEntity> data) {
                return super.shouldFetch(data) && !local;
//                return local;
            }

            @NonNull
            @Override
            protected List<SysCategoryEntity> loadFromDb() {
                List<SysCategoryEntity> sysCategoryEntities = syscategoryDao.loadList();
                return sysCategoryEntities;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysCategoryEntity>>> createCall() {
                return syscategoryWebService.list();
            }
        }.getAsLiveData();
    }


}
