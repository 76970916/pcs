package com.spe.dcs.system.syslistbutton;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:列表按钮
 * Author.
 * Data:${DATA}
 */

public class SysListButtonViewModel extends ViewModel {

    private static final String TAG = "SysFormFieldTypeViewModel";
    @Inject
    public SysListButtonResposity sysListButtonResposity;

    @Inject
    public SysListButtonViewModel() {
//        Log.d(TAG, "SysFormFieldViewModel被创建了");
    }

    /**
     * @param local
     * @param
     * @return
     */
    public LiveData<Resource<List<SysListButtonEntity>>> list(boolean local) {
        return sysListButtonResposity.list(local);

    }
}
