package com.spe.dcs.project.moneprojectinfo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:子项目
 * Author.
 * Data:
 */
public class MOneProjectInfoViewModel extends ViewModel {
    private static final String TAG = "CPipeline";
    @Inject
    public MOneProjectInfoRepository MOneProjectInfoRepository;

    @Inject
    public MOneProjectInfoViewModel() {
        Log.d(TAG, "CPipelineViewModel被创建了");
    }


    public LiveData<Resource<List<MOneProjectInfoEntity>>> list(boolean local) {
        return MOneProjectInfoRepository.list(local);

    }
    //第二期修改的,先修改第一张表,先暂时保留第一期的list（）接口,
    public LiveData<Resource<List<MOneProjectInfoEntity>>> lists(boolean local, String projectNumber) {
        return MOneProjectInfoRepository.lists(local, projectNumber);

    }

}
