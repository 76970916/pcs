package com.spe.dcs.tree;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

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
 * 文件名：TreeViewModel.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 9:34
 * 描  述： 树
 */
public class TreeViewModel extends ViewModel {
    private static final String TAG = "TreeViewModel";

    @Inject
    public TreeRepository treeRepository;

    @Inject
    public TreeViewModel() {
        Log.d(TAG, "TreeViewModel 被创建");
    }


    public LiveData<Resource<RespEntity>> upgrade() {
        return treeRepository.upgrade();
    }

    public LiveData<Resource<RespEntity>> findNewVersion() {
        return treeRepository.findNewVersion();
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
        return treeRepository.uploadLog(parts);

    }
}
