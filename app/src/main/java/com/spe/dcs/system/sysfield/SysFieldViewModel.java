package com.spe.dcs.system.sysfield;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:字段表
 * Author.
 * Data:
 */
public class SysFieldViewModel extends ViewModel {
    private static final String TAG = "SysField";
    @Inject
    public SysFieldRepository sysFieldRepository;

    @Inject
    public SysFieldViewModel() {
        Log.d(TAG, "SysFieldViewModel被创建了");
    }


    public LiveData<Resource<List<SysFieldEntity>>> list(boolean local, String tableCode) {
        return sysFieldRepository.list(local, tableCode);

    }

}
