package com.spe.dcs.system.syslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:列表
 * Author.
 * Data:
 */
public class SysListViewModel extends ViewModel {
    private static final String TAG = "SysList";
    @Inject
    public SysListRepository sysListRepository;

    @Inject
    public SysListViewModel() {
        Log.d(TAG, "SysListViewModel被创建了");
    }


    public LiveData<Resource<List<SysListEntity>>> list(boolean local) {
        return sysListRepository.list(local);

    }
}
