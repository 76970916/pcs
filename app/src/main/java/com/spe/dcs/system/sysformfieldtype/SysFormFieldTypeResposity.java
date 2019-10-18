package com.spe.dcs.system.sysformfieldtype;

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

public class SysFormFieldTypeResposity {
    private final SysFormFieldTypeDao sysFormFieldTypeDao;
    private final SysFormFieldTypeWebService sysFormFieldTypeWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysFormFieldTypeResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysFormFieldTypeWebService = retrofit.create(SysFormFieldTypeWebService.class);
        this.sysFormFieldTypeDao = sysDatabase.sysFormFieldTypeDao();
        this.sysDatabase = sysDatabase;

    }


    public LiveData<Resource<List<SysFormFieldTypeEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysFormFieldTypeEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysFormFieldTypeEntity> item) {
                    sysFormFieldTypeDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysFormFieldTypeEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysFormFieldTypeEntity> loadFromDb() {
                return sysFormFieldTypeDao.loadList();
//                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysFormFieldTypeEntity>>> createCall() {
                return sysFormFieldTypeWebService.list();
            }
        }.getAsLiveData();
    }

}
