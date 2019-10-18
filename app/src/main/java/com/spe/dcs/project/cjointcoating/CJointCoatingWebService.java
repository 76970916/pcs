package com.spe.dcs.project.cjointcoating;

import android.arch.lifecycle.LiveData;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.net.FlagRespEntity;
import com.spe.dcs.app.net.RespEntity;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Desc:06_防腐补口
 * Author.
 * Data:
 */
public interface CJointCoatingWebService {
    @Multipart
    @POST("/cjointcoating/save")
    LiveData<Resource<RespEntity>> save(@PartMap Map<String, RequestBody> params, @Query("input_hidden_tablename") String tableName);


    @POST("/cjointcoating/dataReport")
    LiveData<Resource<FlagRespEntity>> report(@Query("id") String id);

    @POST("/cjointcoating/getDataListByStatus")
    LiveData<Resource<Response<List<CJointCoatingEntity>>>> list(@Query("page") int page, @Query("rows") int rows, @Query("tableId") String tableName, @Query("status") String status);

    @POST("/cjointcoating/deleteById")
    LiveData<Resource<RespEntity>> delete(@Query("id") String id);

    @POST("/cjointcoating/test")
    LiveData<Resource<RespEntity>> revoke(@Body List<CJointCoatingEntity> entities);

    @POST("/cjointcoating/test")
    LiveData<Resource<RespEntity>> revoke(@Query("id") String id);

    @POST("/cjointcoating/completeTaskJudge")
    LiveData<Resource<FlagRespEntity>> completeTaskJudge(@Query("id") String id, @Query("judge") String judge, @Query("status") String status, @Query("comment") String comment);

    @POST("/process/revoke")
    LiveData<Resource<FlagRespEntity>> revoked(@Query("id") String id, @Query("tableName") String tableName, @Query("status") String status);

    @POST("/util/getPhotoByName")
    LiveData<Resource<String>> getPic(@Query("fileName") String fileName);
    @POST("/cjointcoating/findByIdApp")
    LiveData<Resource<RespEntity>> findUpdateId(@Query("id") String id);

}
