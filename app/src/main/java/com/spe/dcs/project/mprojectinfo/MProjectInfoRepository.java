package com.spe.dcs.project.mprojectinfo;

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
 * Desc:59.1项目
 * Author.
 * Data:
 */

public class MProjectInfoRepository {

    private final MProjectInfoDao cprojectInfoDao;
    private final MProjectInfoWebService cprojectInfoWebService;

    @Inject
    public MProjectInfoRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cprojectInfoWebService = retrofit.create(MProjectInfoWebService.class);
        this.cprojectInfoDao = pcsDatabase.cProjectDao();
    }

    public LiveData<Resource<List<MProjectInfoEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<MProjectInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MProjectInfoEntity> item) {
                cprojectInfoDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MProjectInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MProjectInfoEntity> loadFromDb() {
                return cprojectInfoDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MProjectInfoEntity>>> createCall() {
                return cprojectInfoWebService.list();
            }
        }.getAsLiveData();
    }

}
