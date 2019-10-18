package com.spe.dcs.system.sysuser;

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
 * Desc:用户
 * Author.
 * Data:
 */

public class SysUserRepository {

    private final SysUserDao sysuserDao;
    private final SysUserWebService sysuserWebService;


    @Inject
    public SysUserRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysuserWebService = retrofit.create(SysUserWebService.class);
        this.sysuserDao = sysDatabase.sysUserDao();
    }


    public LiveData<Resource<List<SysUserEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysUserEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysUserEntity> item) {
                sysuserDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysUserEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysUserEntity> loadFromDb() {
                return sysuserDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysUserEntity>>> createCall() {
                return sysuserWebService.list();
            }
        }.getAsLiveData();
    }


}
