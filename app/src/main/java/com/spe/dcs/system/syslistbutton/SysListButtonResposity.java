package com.spe.dcs.system.syslistbutton;

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
 * Desc:列表按钮
 * Author.
 * Data:${DATA}
 */

public class SysListButtonResposity {
    private final SysListButtonDao sysListButtonDao;
    private final SysListButtonWebService sysListButtonWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysListButtonResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysListButtonWebService = retrofit.create(SysListButtonWebService.class);
        this.sysListButtonDao = sysDatabase.sysListButtonDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysListButtonEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysListButtonEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysListButtonEntity> item) {
                    sysListButtonDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysListButtonEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysListButtonEntity> loadFromDb() {
                return sysListButtonDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysListButtonEntity>>> createCall() {
                return sysListButtonWebService.list();
            }
        }.getAsLiveData();
    }

}
