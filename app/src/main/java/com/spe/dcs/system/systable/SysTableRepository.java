package com.spe.dcs.system.systable;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Desc:表
 * Author.
 * Data:
 */

public class SysTableRepository {

    private final SysTableDao systableDao;
    private final SysTableWebService systableWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysTableRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.systableWebService = retrofit.create(SysTableWebService.class);
        this.systableDao = sysDatabase.sysTableDao();
        this.sysDatabase = sysDatabase;
    }

    public LiveData<Resource<List<SysTableEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysTableEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysTableEntity> item) {
                    systableDao.save(item);
            }

            @Override
            protected boolean shouldFetch(List<SysTableEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysTableEntity> loadFromDb() {
                return systableDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysTableEntity>>> createCall() {
                return systableWebService.list();
            }
        }.getAsLiveData();
    }


}
