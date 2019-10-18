package com.spe.dcs.common;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

/**
 * 文件名：ImageViewModel.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 11:26
 * 描  述： 二维码扫描
 */
public class ImageViewModel extends ViewModel {
    private static final String TAG = "QrcodeViewModel";

    @Inject
    public ImageViewModel() {
        Log.d(TAG, "TreeViewModel 被创建");
    }

}
