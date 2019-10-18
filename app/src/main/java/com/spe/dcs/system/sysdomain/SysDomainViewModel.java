package com.spe.dcs.system.sysdomain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:域表
 * Author.
 * Data:
 */
public class SysDomainViewModel extends ViewModel {
    private static final String TAG = "SysDomain";
    @Inject
    public SysDomainRepository sysDomainRepository;

    @Inject
    public SysDomainViewModel() {
        Log.d(TAG, "SysDomainViewModel被创建了");
    }

    /**
     * @param local
     * @param type  表名_字段 大写
     * @return
     */
    public LiveData<Resource<List<SysDomainEntity>>> list(boolean local, String type) {
        return sysDomainRepository.list(local, type);

    }

}
