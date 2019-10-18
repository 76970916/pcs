package com.spe.dcs.system.systable;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:表
 * Author.
 * Data:
 */
public class SysTableViewModel extends ViewModel {
    private static final String TAG = "SysTable";
    @Inject
    public SysTableRepository sysTableRepository;

    @Inject
    public SysTableViewModel() {
        Log.d(TAG, "SysTableViewModel被创建了");
    }


    public LiveData<Resource<List<SysTableEntity>>> list(boolean loacal) {
        return sysTableRepository.list(loacal);

    }

}
