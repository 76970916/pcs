package com.spe.dcs.project.cweldingprocedure;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Desc:焊接工艺规程
 * Author.
 * Data:${DATA}
 */

public class CWeldingProcedureResposity {
    private final CWeldingProcedureDao cWeldingProcedureDao;
    private final CWeldingProcedureWebService cWeldingProcedureWebService;

    @Inject
    public CWeldingProcedureResposity(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cWeldingProcedureWebService = retrofit.create(CWeldingProcedureWebService.class);
        this.cWeldingProcedureDao = pcsDatabase.cWeldingProcedureDao();
    }

    public LiveData<Resource<List<CWeldingProcedureEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<CWeldingProcedureEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldingProcedureEntity> item) {
                cWeldingProcedureDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWeldingProcedureEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<CWeldingProcedureEntity> loadFromDb() {
                return cWeldingProcedureDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldingProcedureEntity>>> createCall() {
                return cWeldingProcedureWebService.list();
            }
        }.getAsLiveData();
    }

}
