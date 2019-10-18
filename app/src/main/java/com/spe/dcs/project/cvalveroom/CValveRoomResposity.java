package com.spe.dcs.project.cvalveroom;

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
 * Desc:阀室
 * Author.
 * Data:${DATA}
 */

public class CValveRoomResposity {
    private final CValveRoomDao cValveRoomDao;
    private final CValveRoomWebService cValveRoomWebService;

    @Inject
    public CValveRoomResposity(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cValveRoomWebService = retrofit.create(CValveRoomWebService.class);
        this.cValveRoomDao = pcsDatabase.cValveRoomDao();
    }

    public LiveData<Resource<List<CValveRoomEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<CValveRoomEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CValveRoomEntity> item) {
                cValveRoomDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CValveRoomEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<CValveRoomEntity> loadFromDb() {
                return cValveRoomDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CValveRoomEntity>>> createCall() {
                return cValveRoomWebService.list();
            }
        }.getAsLiveData();
    }

}
