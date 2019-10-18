package com.spe.dcs.system.syscategory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:分类表
 * Author.
 * Data:
 */
public class SysCategoryViewModel extends ViewModel {
    private static final String TAG = "SysCategory";
    @Inject
    public SysCategoryRepository sysCategoryRepository;

    @Inject
    public SysCategoryViewModel() {
        Log.d(TAG, "SysCategoryViewModel被创建了");
    }


    /**
     * @param local 是否读取本地数据
     * @return
     */
    public LiveData<Resource<List<SysCategoryEntity>>> list(boolean local) {
        return sysCategoryRepository.list(local);

    }
}
