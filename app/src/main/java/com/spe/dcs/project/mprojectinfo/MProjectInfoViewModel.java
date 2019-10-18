package com.spe.dcs.project.mprojectinfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:59.1项目
 * Author.
 * Data:
 */
public class MProjectInfoViewModel extends ViewModel {
    private static final String TAG = "MProjectInfoViewModel";
    @Inject
    public MProjectInfoRepository mProjectInfoRepository;

    @Inject
    public MProjectInfoViewModel() {
        Log.d(TAG, "MProjectInfoViewModel被创建了");
    }

    public LiveData<Resource<List<MProjectInfoEntity>>> list(boolean local) {
        return mProjectInfoRepository.list(local);

    }

}
