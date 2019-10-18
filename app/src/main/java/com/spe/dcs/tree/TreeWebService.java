package com.spe.dcs.tree;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.RespEntity;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 文件名：TreeWebService.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 9:33
 * 描  述： 树-节点数据
 */
public interface TreeWebService {

    @Multipart
    @POST("/sysAndroidError/save")
    LiveData<Resource<RespEntity>> uploadLog(@Part() List<MultipartBody.Part> parts);

    @POST("/sysAndroidUpdate/downNewestApk")
    LiveData<Resource<RespEntity>> upgrade();

    @POST("/sysAndroidUpdate/findNewestVersion")
    LiveData<Resource<RespEntity>> findNewVersion();

    @Multipart
    @POST("/sysAndroidError/save")
    LiveData<Resource<RespEntity>> uploadLog(@Part MultipartBody.Part parts);

}
