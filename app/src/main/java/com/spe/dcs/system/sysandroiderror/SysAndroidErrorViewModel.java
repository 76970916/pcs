package com.spe.dcs.system.sysandroiderror;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.utility.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Desc:错误日志
 * Author.
 * Data:${DATA}
 */

public class SysAndroidErrorViewModel extends ViewModel {
    private static final String TAG = "SysAndroidErrorViewModel";

    @Inject
    public SysAndroidErrorResposity sysAndroidResposity;

    @Inject
    public SysAndroidErrorViewModel() {
//        Log.d(TAG, "SysAndroidViewModel被创建了");
    }

    public LiveData<Resource<RespEntity>> uploadLog() {
        File dir = new File(FileUtils.LOG);
        List<MultipartBody.Part> parts = null;
        if (dir.exists()) {
            File[] files = dir.listFiles();
            parts = new ArrayList<>();
            for (File f : files) {
                if (f.getAbsolutePath().lastIndexOf("log") != -1) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), f);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", f.getName(), requestBody);
                    parts.add(part);
                }
            }
        }
        if (parts == null || parts.isEmpty()) return new MediatorLiveData<>();
        return sysAndroidResposity.uploadLog(parts);

    }

}
