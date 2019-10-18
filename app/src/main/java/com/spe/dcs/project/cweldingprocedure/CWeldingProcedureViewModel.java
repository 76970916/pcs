package com.spe.dcs.project.cweldingprocedure;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:焊接工艺规程
 * Author.
 * Data:${DATA}
 */

public class CWeldingProcedureViewModel extends ViewModel {
    private static final String TAG = "CWeldingProcedureViewModel";

    @Inject
    public CWeldingProcedureResposity cWeldingProcedureResposity;

    @Inject
    public CWeldingProcedureViewModel() {
//        Log.d(TAG, "CWeldingProcedureViewModel 被创建了");
    }


    public LiveData<Resource<List<CWeldingProcedureEntity>>> list(boolean local) {
        return cWeldingProcedureResposity.list(local);

    }
}
