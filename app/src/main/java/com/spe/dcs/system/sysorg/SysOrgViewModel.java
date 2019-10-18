package com.spe.dcs.system.sysorg;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class SysOrgViewModel extends ViewModel {
    private static final String TAG = "SysOrgViewModel";
    @Inject
    public SysOrgRepository sysOrgRepository;

    @Inject
    public SysOrgViewModel() {
        Log.d(TAG, "SysOrgViewModel被创建了");
    }


    /**
     * @param local 是否读取本地数据
     * @return
     */
    public LiveData<Resource<List<SysOrgEntity>>> list(boolean local) {
        return sysOrgRepository.list(local);

    }

    /**
     * @param local 是否读取本地数据
     * @return
     */
    public LiveData<Resource<List<String>>> lists(boolean local, String groupid) {
        return sysOrgRepository.lists(local, groupid);
    }
    /**
     * @paraml     * @return
     */
    public LiveData<Resource<SysOrgEntity>> findDataByCode(String code) {
        return sysOrgRepository.findDataByCode(code);
    }

}
