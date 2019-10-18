package com.spe.dcs.tree;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.app.net.RespEntity;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;

/**
 * 文件名：TreeRepository.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 9:35
 * 描  述： 树
 */
public class TreeRepository {

    private final TreeWebService treeWebService;

    @Inject
    public TreeRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.treeWebService = retrofit.create(TreeWebService.class);
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
                return treeWebService.uploadLog(parts.get(0));
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<RespEntity>> upgrade() {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {

            }

            @NonNull
            @Override
            @WorkerThread
            protected RespEntity loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return treeWebService.upgrade();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<RespEntity>> findNewVersion() {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {

            }

            @NonNull
            @Override
            @WorkerThread
            protected RespEntity loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return treeWebService.findNewVersion();
            }
        }.getAsLiveData();
    }
}