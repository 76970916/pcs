package com.spe.dcs.project.moneprojectinfo;

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
 * Desc:子项目
 * Author.
 * Data:
 */

public class MOneProjectInfoRepository {

    private final MOneProjectInfoDao cpipelineDao;
    private final MOneProjectInfoWebService cpipelineWebService;

    @Inject
    public MOneProjectInfoRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cpipelineWebService = retrofit.create(MOneProjectInfoWebService.class);
        this.cpipelineDao = pcsDatabase.cPipelineDao();
    }


    public LiveData<Resource<List<MOneProjectInfoEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<MOneProjectInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MOneProjectInfoEntity> item) {
                cpipelineDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MOneProjectInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MOneProjectInfoEntity> loadFromDb() {
                return cpipelineDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MOneProjectInfoEntity>>> createCall() {
                return cpipelineWebService.list();
            }
        }.getAsLiveData();
    }

//第二期修改的,先暂时保留第一期的list（）接口,
    public LiveData<Resource<List<MOneProjectInfoEntity>>> lists(boolean local, String projectNumber) {
        return new NetworkBoundResource<List<MOneProjectInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MOneProjectInfoEntity> item) {
                cpipelineDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MOneProjectInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MOneProjectInfoEntity> loadFromDb() {
                return cpipelineDao.loadLists(projectNumber);
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MOneProjectInfoEntity>>> createCall() {
                return cpipelineWebService.lists(projectNumber);
            }
        }.getAsLiveData();
    }

}
