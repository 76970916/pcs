package com.spe.dcs.system.syslist;

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
 * Desc:列表
 * Author.
 * Data:
 */

public class SysListRepository {

    private final SysListDao syslistDao;
    private final SysListWebService syslistWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysListRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.syslistWebService = retrofit.create(SysListWebService.class);
        this.syslistDao = sysDatabase.sysListDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysListEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysListEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysListEntity> item) {
                syslistDao.save(item);
                List<SysListEntity> sysListEntities = syslistDao.loadList();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysListEntity> data) {
                return super.shouldFetch(data) && !local;
//                return local;
            }

            @NonNull
            @Override
            protected List<SysListEntity> loadFromDb() {
                List<SysListEntity> sysListEntities = syslistDao.loadList();
                return sysListEntities;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysListEntity>>> createCall() {
                return syslistWebService.list();
            }
        }.getAsLiveData();
    }

}
