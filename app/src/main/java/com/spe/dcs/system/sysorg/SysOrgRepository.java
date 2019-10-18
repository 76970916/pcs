package com.spe.dcs.system.sysorg;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Desc:ORG表
 * Author.
 * Data:${DATA}
 */

public class SysOrgRepository {

    private final SysOrgDao sysorgDao;
    private final SysOrgWebService sysorgWebService;
    private final SysDatabase sysDatabase;

    @Inject
    public SysOrgRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysorgWebService = retrofit.create(SysOrgWebService.class);
        this.sysorgDao = sysDatabase.sysOrgDao();
        this.sysDatabase = sysDatabase;
    }

    public LiveData<Resource<List<SysOrgEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysOrgEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysOrgEntity> item) {
                sysorgDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysOrgEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysOrgEntity> loadFromDb() {
                return sysorgDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysOrgEntity>>> createCall() {
                return sysorgWebService.list();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<String>>> lists(boolean local, String groupid) {
        return new NetworkBoundResource<List<String>>() {
            @Override
            protected void saveCallResult(@NonNull List<String> item) {
//                sysorgDao.save(item);
                Log.d("", "11111111111111111111");
            }

            @Override
            protected boolean shouldFetch(@Nullable List<String> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<String> loadFromDb() {
                return sysorgDao.loadLists(groupid);
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<String>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<List<SysOrgEntity>>> listss(boolean local, String groupId) {
        return new NetworkBoundResource<List<SysOrgEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysOrgEntity> item) {
                sysorgDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysOrgEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysOrgEntity> loadFromDb() {
                return sysorgDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysOrgEntity>>> createCall() {
                return sysorgWebService.list();
            }
        }.getAsLiveData();
    }
    public LiveData<Resource<SysOrgEntity>> findDataByCode( String code) {
        return new NetworkBoundResource<SysOrgEntity>() {
            @Override
            protected void saveCallResult(@NonNull SysOrgEntity item) {
            }
            @NonNull
            @Override
            protected SysOrgEntity loadFromDb() {
                return sysorgDao.findDataByCode(code);
            }

            @NonNull
            @Override
            protected LiveData<Resource<SysOrgEntity>> createCall() {
                return sysorgWebService.findDataByCode(code);
            }
        }.getAsLiveData();
    }
}
