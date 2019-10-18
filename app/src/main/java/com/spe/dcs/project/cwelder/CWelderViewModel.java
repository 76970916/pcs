package com.spe.dcs.project.cwelder;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:施工焊工数据
 * Author.
 * Data:${DATA}
 */

public class CWelderViewModel extends ViewModel {

    private static final String TAG = "CWelderViewModel";

    @Inject
    public CWelderResposity cWelderResposity;

    @Inject
    public CWelderViewModel() {
        Log.d(TAG, "CWelderViewModel被创建了");
    }


    public LiveData<Resource<List<CWelderEntity>>> list(boolean local) {
        return cWelderResposity.list(local);

    }
}
