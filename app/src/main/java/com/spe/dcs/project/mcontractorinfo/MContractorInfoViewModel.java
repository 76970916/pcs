package com.spe.dcs.project.mcontractorinfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:标段
 * Author.
 * Data:
 */
public class MContractorInfoViewModel extends ViewModel {
    private static final String TAG = "MContractorInfoViewModel";
    @Inject
    public MContractorInfoRepository MContractorInfoRepository;

    @Inject
    public MContractorInfoViewModel() {
        Log.d(TAG, "CSectionViewModel被创建了");
    }


    public LiveData<Resource<List<MContractorInfoEntity>>> list(boolean local) {
        return MContractorInfoRepository.list(local);

    }

    public LiveData<Resource<List<MContractorInfoEntity>>> listSupervisior(boolean local) {
        return MContractorInfoRepository.listSupervisior(local);

    }

    public LiveData<Resource<List<MContractorInfoEntity>>> listSupervisiors(boolean local, String projectNumber) {
        return MContractorInfoRepository.listSupervisiors(local, projectNumber);

    }


    public LiveData<Resource<List<MContractorInfoEntity>>> lists(boolean local, String projectNumber) {
        return MContractorInfoRepository.lists(local, projectNumber);

    }


}
