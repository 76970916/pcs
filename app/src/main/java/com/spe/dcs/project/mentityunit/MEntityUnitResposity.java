package com.spe.dcs.project.mentityunit;

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
 * Desc:单元
 * Author.
 * Data:${DATA}
 */

public class MEntityUnitResposity {
    private final MEntityUnitDao mEntityUnitDao;
    private final MEntityUnitWebService mEntityUnitWebService;

    @Inject
    public MEntityUnitResposity(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.mEntityUnitWebService = retrofit.create(MEntityUnitWebService.class);
        this.mEntityUnitDao = pcsDatabase.entityUnitDao();
    }

    public LiveData<Resource<List<MEntityUnitEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<MEntityUnitEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MEntityUnitEntity> item) {
                mEntityUnitDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MEntityUnitEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MEntityUnitEntity> loadFromDb() {
                return mEntityUnitDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MEntityUnitEntity>>> createCall() {
                return mEntityUnitWebService.list();
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<List<MEntityUnitEntity>>> lists(boolean local, String piplineNumber) {
        return new NetworkBoundResource<List<MEntityUnitEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MEntityUnitEntity> item) {
                mEntityUnitDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MEntityUnitEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MEntityUnitEntity> loadFromDb() {
                return mEntityUnitDao.loadLists(piplineNumber);
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MEntityUnitEntity>>> createCall() {
                return mEntityUnitWebService.lists(piplineNumber);
            }
        }.getAsLiveData();
    }

}
