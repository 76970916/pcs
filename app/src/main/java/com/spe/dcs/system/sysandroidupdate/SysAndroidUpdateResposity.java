package com.spe.dcs.system.sysandroidupdate;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.common.NewVersionEntity;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Desc:APP更新
 * Author.
 * Data:${DATA}
 */

public class SysAndroidUpdateResposity {
    private final SysAndroidUpdateWebService sysAndroidUpdateWebService;

    @Inject
    public SysAndroidUpdateResposity(Retrofit retrofit, SysDatabase dcsDatabase) {
        this.sysAndroidUpdateWebService = retrofit.create(SysAndroidUpdateWebService.class);
    }

    public LiveData<Resource<ResponseBody>> upgrade() {
        return new NetworkBoundResource<ResponseBody>() {

            @Override
            protected void saveCallResult(@NonNull ResponseBody item) {

            }

            @NonNull
            @Override
            protected ResponseBody loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<ResponseBody>> createCall() {
                return sysAndroidUpdateWebService.upgrade();
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<NewVersionEntity>> findNewVersion() {
        return new NetworkBoundResource<NewVersionEntity>() {
            @Override
            protected void saveCallResult(@NonNull NewVersionEntity item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable NewVersionEntity data) {
                return true;
            }

            @NonNull
            @Override
            @WorkerThread
            protected NewVersionEntity loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<NewVersionEntity>> createCall() {
                return sysAndroidUpdateWebService.findNewVersion();
            }
        }.getAsLiveData();
    }
}
