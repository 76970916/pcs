package com.spe.dcs.project.cstation;

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
 * Desc:站场
 * Author.
 * Data:${DATA}
 */

public class CStationResposity {

    private final CStationDao cStationDao;
    private final CStationWebService cStationWebService;

    @Inject
    public CStationResposity(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cStationWebService = retrofit.create(CStationWebService.class);
        this.cStationDao = pcsDatabase.cStationDao();
    }

    public LiveData<Resource<List<CStationEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<CStationEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CStationEntity> item) {
                cStationDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CStationEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<CStationEntity> loadFromDb() {
                return cStationDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CStationEntity>>> createCall() {
                return cStationWebService.list();
            }
        }.getAsLiveData();
    }
}
