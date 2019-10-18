package com.spe.dcs.system.sysformfield;

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
 * Desc:表单表
 * Author.
 * Data:${DATA}
 */

public class SysFormFieldResposity {
    private final SysFormFieldDao sysFormFieldDao;
    private final SysFormFieldWebService sysFormFieldWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysFormFieldResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysFormFieldWebService = retrofit.create(SysFormFieldWebService.class);
        this.sysFormFieldDao = sysDatabase.sysFormFieldDao();
        this.sysDatabase = sysDatabase;
    }


    public LiveData<Resource<List<SysFormFieldEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysFormFieldEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysFormFieldEntity> item) {

                    sysFormFieldDao.save(item);


            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysFormFieldEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysFormFieldEntity> loadFromDb() {
                return sysFormFieldDao.loadList();
//                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysFormFieldEntity>>> createCall() {
                return sysFormFieldWebService.list();
            }
        }.getAsLiveData();
    }

}
