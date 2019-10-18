package com.spe.dcs.project.mentityfuction;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:功能区
 * Author.
 * Data:
 */
public class MEntityFuctionViewModel extends ViewModel {
    private static final String TAG = "CPipeSegment";
    @Inject
    public MEntityFuctionRepository MEntityFuctionRepository;

    @Inject
    public MEntityFuctionViewModel() {
        Log.d(TAG, "CPipeSegmentViewModel被创建了");
    }

    public LiveData<Resource<List<MEntityFuctionEntity>>> list(boolean local) {
        return MEntityFuctionRepository.list(local);

    }

    public LiveData<Resource<List<MEntityFuctionEntity>>> lists(boolean local, String sectionNumber) {
        return MEntityFuctionRepository.lists(local, sectionNumber);
    }

//    public LiveData<Resource<List<MEntityFuctionEntity>>> listsType(boolean local, String sectionNumber, String type) {
//        return MEntityFuctionRepository.listsType(local, sectionNumber, type);
//    }

    public LiveData<Resource<List<MEntityFuctionEntity>>> loadList() {
        return MEntityFuctionRepository.loadList();

    }
}
