package com.spe.dcs.project.cweldingunit;

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
 * Desc:施工机组
 * Author.
 * Data:${DATA}
 */

public class CWeldingUnitResposity {
    private final CWeldingUnitDao cWeldingUnitDao;
    private final CWeldingUnitWebService cWeldingUnitWebService;
    private final PcsDatabase pcsDatabase;


    @Inject
    public CWeldingUnitResposity(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cWeldingUnitWebService = retrofit.create(CWeldingUnitWebService.class);
        this.cWeldingUnitDao = pcsDatabase.cWeldingUnitDao();
        this.pcsDatabase = pcsDatabase;
    }

    public LiveData<Resource<List<CWeldingUnitEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<CWeldingUnitEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldingUnitEntity> item) {
                pcsDatabase.beginTransaction();
                try {
                    List<CWeldingUnitEntity> entities = cWeldingUnitDao.loadList();
                    cWeldingUnitDao.del(entities);
                    List<CWeldingUnitEntity> entityList = cWeldingUnitDao.loadList();
//                    cWeldingUnitDao.save(item);
                    List<CWeldingUnitEntity> entityList1 = cWeldingUnitDao.loadList();
                    int size = entityList1.size();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pcsDatabase.endTransaction();
                    cWeldingUnitDao.save(item);
                    List<CWeldingUnitEntity> entityList2 = cWeldingUnitDao.loadList();
                    int size = entityList2.size();
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWeldingUnitEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<CWeldingUnitEntity> loadFromDb() {
                List<CWeldingUnitEntity> entities = cWeldingUnitDao.loadList();
                int size = entities.size();
                return cWeldingUnitDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldingUnitEntity>>> createCall() {
                return cWeldingUnitWebService.list();
            }
        }.getAsLiveData();
    }

}
