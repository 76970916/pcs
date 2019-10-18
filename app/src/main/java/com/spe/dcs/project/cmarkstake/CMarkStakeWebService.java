package com.spe.dcs.project.cmarkstake;

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
 * Desc:29_施工标志桩
 * Author.
 * Data:
 */
public interface CMarkStakeWebService {
    @Multipart
    @POST("/cmarkstake/save")
    LiveData<Resource<RespEntity>> save(@PartMap Map<String, RequestBody> params, @Query("input_hidden_tablename") String tableName);


    @POST("/cmarkstake/dataReport")
    LiveData<Resource<FlagRespEntity>> report(@Query("id") String id);

    @POST("/cmarkstake/getDataListByStatus")
    LiveData<Resource<Response<List<CMarkStakeEntity>>>> list(@Query("page") int page, @Query("rows") int rows, @Query("tableId") String tableName, @Query("status") String status);

    @POST("/cmarkstake/deleteById")
    LiveData<Resource<RespEntity>> delete(@Query("id") String id);

    @POST("/cmarkstake/test")
    LiveData<Resource<RespEntity>> revoke(@Body List<CMarkStakeEntity> entities);

    @POST("/cmarkstake/test")
    LiveData<Resource<RespEntity>> revoke(@Query("id") String id);

    @POST("/cmarkstake/completeTaskJudge")
    LiveData<Resource<FlagRespEntity>> completeTaskJudge(@Query("id") String id, @Query("judge") String judge, @Query("status") String status, @Query("comment") String comment);

    @POST("/process/revoke")
    LiveData<Resource<FlagRespEntity>> revoked(@Query("id") String id, @Query("tableName") String tableName, @Query("status") String status);

    @POST("/util/getPhotoByName")
    LiveData<Resource<String>> getPic(@Query("fileName") String fileName);

}
