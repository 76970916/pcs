package com.spe.dcs.project.mentityfuction;

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
 * Desc:功能区
 * Author.
 * Data:
 */

public class MEntityFuctionRepository {

    private final MEntityFuctionDao cpipesegmentDao;
    private final MEntityFuctionWebService cpipesegmentWebService;

    @Inject
    public MEntityFuctionRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cpipesegmentWebService = retrofit.create(MEntityFuctionWebService.class);
        this.cpipesegmentDao = pcsDatabase.cPipeSegmentDao();
    }


    public LiveData<Resource<List<MEntityFuctionEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<MEntityFuctionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MEntityFuctionEntity> item) {
                cpipesegmentDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MEntityFuctionEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MEntityFuctionEntity> loadFromDb() {
                return cpipesegmentDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MEntityFuctionEntity>>> createCall() {
                return cpipesegmentWebService.list();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<MEntityFuctionEntity>>> lists(boolean local, String sectionNumber) {
        return new NetworkBoundResource<List<MEntityFuctionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MEntityFuctionEntity> item) {
                cpipesegmentDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MEntityFuctionEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MEntityFuctionEntity> loadFromDb() {
                return cpipesegmentDao.loadLists(sectionNumber);
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MEntityFuctionEntity>>> createCall() {
                return cpipesegmentWebService.lists(sectionNumber);
            }
        }.getAsLiveData();
    }

//    public LiveData<Resource<List<MEntityFuctionEntity>>> listsType(boolean local, String sectionNumber, String xld) {
//        return new NetworkBoundResource<List<MEntityFuctionEntity>>() {
//            @Override
//            protected void saveCallResult(@NonNull List<MEntityFuctionEntity> item) {
//                cpipesegmentDao.save(item);
//            }
//
//            @Override
//            protected boolean shouldFetch(@Nullable List<MEntityFuctionEntity> data) {
//                return super.shouldFetch(data) && !local;
//            }
//
//            @NonNull
//            @Override
//            protected List<MEntityFuctionEntity> loadFromDb() {
//                return cpipesegmentDao.loadListsType(sectionNumber, xld);
//            }
//
//            @NonNull
//            @Override
//            protected LiveData<Resource<List<MEntityFuctionEntity>>> createCall() {
//                return cpipesegmentWebService.lists(sectionNumber);
//            }
//        }.getAsLiveData();
//    }



    public LiveData<Resource<List<MEntityFuctionEntity>>> loadList() {
        return new NetworkBoundResource<List<MEntityFuctionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MEntityFuctionEntity> item) {
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MEntityFuctionEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected List<MEntityFuctionEntity> loadFromDb() {
                return cpipesegmentDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MEntityFuctionEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }
}
