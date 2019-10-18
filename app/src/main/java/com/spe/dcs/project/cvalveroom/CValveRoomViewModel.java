package com.spe.dcs.project.cvalveroom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:阀室
 * Author.
 * Data:${DATA}
 */

public class CValveRoomViewModel extends ViewModel {
    private static final String TAG = "CValveRoomViewModel";

    @Inject
    public CValveRoomResposity cValveRoomResposity;

    @Inject
    public CValveRoomViewModel() {
//        Log.d(TAG, "CWeldingProcedureViewModel 被创建了");
    }


    public LiveData<Resource<List<CValveRoomEntity>>> list(boolean local) {
        return cValveRoomResposity.list(local);

    }
}
