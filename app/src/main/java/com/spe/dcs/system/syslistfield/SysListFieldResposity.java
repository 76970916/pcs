package com.spe.dcs.system.syslistfield;

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
 * Desc:列表字段
 * Author.
 * Data:${DATA}
 */

public class SysListFieldResposity {
    private final SysListFieldDao sysListFieldDao;
    private final SysListFieldWebService sysListFieldWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysListFieldResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysListFieldWebService = retrofit.create(SysListFieldWebService.class);
        this.sysListFieldDao = sysDatabase.sysListFieldDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysListFieldEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysListFieldEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysListFieldEntity> item) {
                    sysListFieldDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysListFieldEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysListFieldEntity> loadFromDb() {
                return sysListFieldDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysListFieldEntity>>> createCall() {
                return sysListFieldWebService.list();
            }
        }.getAsLiveData();
    }

}
