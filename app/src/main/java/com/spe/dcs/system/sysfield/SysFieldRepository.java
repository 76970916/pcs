package com.spe.dcs.system.sysfield;

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
 * Desc:字段表
 * Author.
 * Data:
 */

public class SysFieldRepository {

    private final SysFieldDao sysfieldDao;
    private final SysFieldWebService sysfieldWebService;
    private final SysDatabase sysDatabase;


    @Inject
    public SysFieldRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysfieldWebService = retrofit.create(SysFieldWebService.class);
        this.sysfieldDao = sysDatabase.sysFieldDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysFieldEntity>>> list(boolean local, String tableCode) {
        return new NetworkBoundResource<List<SysFieldEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysFieldEntity> item) {
                sysfieldDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysFieldEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysFieldEntity> loadFromDb() {
                return sysfieldDao.loadList(tableCode);
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysFieldEntity>>> createCall() {
                return sysfieldWebService.list();
            }
        }.getAsLiveData();
    }

}
