package com.spe.dcs.project.cwelder;

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
 * Desc:施工焊工数据
 * Author.
 * Data:${DATA}
 */

public class CWelderResposity {

    private final CWelderDao cWelderDao;
    private final CWelderWebService cWelderWebService;
    private final PcsDatabase pcsDatabase;

    @Inject
    public CWelderResposity(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cWelderWebService = retrofit.create(CWelderWebService.class);
        this.cWelderDao = pcsDatabase.cWelderDao();
        this.pcsDatabase = pcsDatabase;
    }

    public LiveData<Resource<List<CWelderEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<CWelderEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWelderEntity> item) {
                pcsDatabase.beginTransaction();
                try {
                    List<CWelderEntity> entities = cWelderDao.loadList();
                    cWelderDao.del(entities);
                    cWelderDao.save(item);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pcsDatabase.endTransaction();
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWelderEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<CWelderEntity> loadFromDb() {
                return cWelderDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWelderEntity>>> createCall() {
                return cWelderWebService.list();
            }
        }.getAsLiveData();
    }

}
