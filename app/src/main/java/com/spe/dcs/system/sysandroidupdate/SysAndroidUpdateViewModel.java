package com.spe.dcs.system.sysandroidupdate;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;
import com.spe.dcs.common.NewVersionEntity;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Desc:APP更新
 * Author.
 * Data:${DATA}
 */

public class SysAndroidUpdateViewModel extends ViewModel {

    private static final String TAG = "SysAndroidUpdateViewModel";

    @Inject
    public SysAndroidUpdateResposity sysAndroidUpdateResposity;

    @Inject
    public SysAndroidUpdateViewModel() {
//        Log.d(TAG, "TreeViewModel 被创建");
    }

    public LiveData<Resource<ResponseBody>> upgrade() {
        return sysAndroidUpdateResposity.upgrade();
    }


    public LiveData<Resource<NewVersionEntity>> findNewVersion() {
        return sysAndroidUpdateResposity.findNewVersion();
    }
}
