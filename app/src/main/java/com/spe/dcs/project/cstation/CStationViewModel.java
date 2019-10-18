package com.spe.dcs.project.cstation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:站场
 * Author.
 * Data:${DATA}
 */

public class CStationViewModel extends ViewModel {
    private static final String TAG = "CStationViewModel";

    @Inject
    public CStationResposity cStationResposity;

    @Inject
    public CStationViewModel() {
//        Log.d(TAG, "CWeldingProcedureViewModel 被创建了");
    }


    public LiveData<Resource<List<CStationEntity>>> list(boolean local) {
        return cStationResposity.list(local);

    }

}
