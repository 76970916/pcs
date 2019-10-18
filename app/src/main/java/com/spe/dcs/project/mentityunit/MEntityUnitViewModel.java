package com.spe.dcs.project.mentityunit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:单元
 * Author.
 * Data:${DATA}
 */

public class MEntityUnitViewModel extends ViewModel {
    private static final String TAG = "MEntityUnitViewModel";
    @Inject
    public MEntityUnitResposity mEntityUnitResposity;

    @Inject
    public MEntityUnitViewModel() {
        Log.d(TAG, "MEntityUnitViewModel被创建了");
    }


    public LiveData<Resource<List<MEntityUnitEntity>>> list(boolean local) {
        return mEntityUnitResposity.list(local);

    }


    public LiveData<Resource<List<MEntityUnitEntity>>> lists(boolean local, String piplineNumber) {
        return mEntityUnitResposity.lists(local, piplineNumber);

    }
}
