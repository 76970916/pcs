package com.spe.dcs.system.sysandroiderror;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.app.net.RespEntity;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;

/**
 * Desc:错误日志
 * Author.
 * Data:${DATA}
 */

public class SysAndroidErrorResposity {
    private final SysAndroidErrorDao sysAndroidErrorDao;
    private final SysAndroidWebService sysAndroidWebService;

    @Inject
    public SysAndroidErrorResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysAndroidWebService = retrofit.create(SysAndroidWebService.class);
        this.sysAndroidErrorDao = sysDatabase.sysAndroidErrorDao();
    }

    public LiveData<Resource<RespEntity>> uploadLog(List<MultipartBody.Part> parts) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
            }

            @Override
            protected boolean shouldFetch(@Nullable RespEntity data) {
                return true;
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return sysAndroidWebService.uploadLog(parts.get(0));
            }
        }.getAsLiveData();
    }


}
