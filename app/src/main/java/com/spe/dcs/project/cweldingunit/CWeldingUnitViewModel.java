package com.spe.dcs.project.cweldingunit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class CWeldingUnitViewModel extends ViewModel {
    private static final String TAG = "CWeldingUnitViewModel";

    @Inject
    public CWeldingUnitResposity cWeldingUnitResposity;

    @Inject
    public CWeldingUnitViewModel() {
        Log.d(TAG, "CWeldingUnitViewModel被创建了");
    }


    public LiveData<Resource<List<CWeldingUnitEntity>>> list(boolean local) {
        return cWeldingUnitResposity.list(local);

    }

}
