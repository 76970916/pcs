package com.spe.dcs.project.mcontractorinfo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoDao;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoEntity;
import com.spe.dcs.project.mprojectinfo.MProjectInfoDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Desc:59.3施工标段
 * Author.
 * Data: U:代表监理单位   C：代表施工单位和标段
 */

public class MContractorInfoRepository {

    private final MContractorInfoDao csectionDao;
    private final MOneProjectInfoDao mOneProjectInfoDao;
    private final MContractorInfoWebService csectionWebService;

    @Inject
    public MContractorInfoRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.csectionWebService = retrofit.create(MContractorInfoWebService.class);
        this.csectionDao = pcsDatabase.cSectionDao();
        this.mOneProjectInfoDao = pcsDatabase.cPipelineDao();
    }

    public LiveData<Resource<List<MContractorInfoEntity>>> listSupervisior(boolean local) {
        return new NetworkBoundResource<List<MContractorInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MContractorInfoEntity> item) {
                csectionDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MContractorInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MContractorInfoEntity> loadFromDb() {
                return csectionDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MContractorInfoEntity>>> createCall() {
                return csectionWebService.listSupervisior();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<MContractorInfoEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<MContractorInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MContractorInfoEntity> item) {
                csectionDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MContractorInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MContractorInfoEntity> loadFromDb() {
                return csectionDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MContractorInfoEntity>>> createCall() {
                return csectionWebService.list();
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<List<MContractorInfoEntity>>> lists(boolean local, String projectNumber) {
        return new NetworkBoundResource<List<MContractorInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MContractorInfoEntity> item) {
                csectionDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MContractorInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MContractorInfoEntity> loadFromDb() {
                //根据项目选出子项目
                List<MOneProjectInfoEntity> mOneProjectInfoEntities = mOneProjectInfoDao.loadLists(projectNumber);
                List<MContractorInfoEntity> mContractorInfoEntities = csectionDao.loadList();
                List<MContractorInfoEntity> mContractorInfoEntityList = new ArrayList<>();
//                根据子项目选出标段
                for (int i = 0; i < mOneProjectInfoEntities.size(); i++) {
//                   如果子项目id和标段中的子项目编号相等，就保存到集合中
                    for (int j = 0; j < mContractorInfoEntities.size(); j++) {
                        if (mContractorInfoEntities != null && mOneProjectInfoEntities.size() != 0) {
                            if (mOneProjectInfoEntities.get(i).getId().equals(mContractorInfoEntities.get(j).getOneProjectNum())) {
                                mContractorInfoEntityList.add(mContractorInfoEntities.get(j));
                            }
                        }
                    }
                }

                return mContractorInfoEntityList;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MContractorInfoEntity>>> createCall() {
                return null;
//                return csectionWebService.lists(piplineNumber);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<MContractorInfoEntity>>> listSupervisiors(boolean local, String projectNumber) {
        return new NetworkBoundResource<List<MContractorInfoEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<MContractorInfoEntity> item) {
                csectionDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MContractorInfoEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<MContractorInfoEntity> loadFromDb() {
                //根据项目选出子项目
                List<MOneProjectInfoEntity> mOneProjectInfoEntities = mOneProjectInfoDao.loadLists(projectNumber);
                //获取所有的监理单位
                List<MContractorInfoEntity> mContractorInfoEntities = csectionDao.loadLists("U");
                List<MContractorInfoEntity> mContractorInfoEntityList = new ArrayList<>();
//                根据子项目选出对应的监理单位
                for (int i = 0; i < mOneProjectInfoEntities.size(); i++) {
//                   如果子项目id和标段中的子项目编号相等，就保存到集合中
                    for (int j = 0; j < mContractorInfoEntities.size(); j++) {
                        if (mContractorInfoEntities != null && mOneProjectInfoEntities.size() != 0) {
                            if (mOneProjectInfoEntities.get(i).getId().equals(mContractorInfoEntities.get(j).getOneProjectNum())) {
                                mContractorInfoEntityList.add(mContractorInfoEntities.get(j));
                            }
                        }
                    }
                }

                return mContractorInfoEntityList;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<MContractorInfoEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

}
