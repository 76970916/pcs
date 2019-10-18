package com.spe.dcs.project.chydraulicprotection;

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
 * Desc:25_施工水工保护
 * Author.
 * Data:
 */
public interface CHydraulicProtectionWebService {
    @Multipart
    @POST("/chydraulicprotection/save")
    LiveData<Resource<RespEntity>> save(@PartMap Map<String, RequestBody> params, @Query("input_hidden_tablename") String tableName);


    @POST("/chydraulicprotection/dataReport")
    LiveData<Resource<FlagRespEntity>> report(@Query("id") String id);

    @POST("/chydraulicprotection/getDataListByStatus")
    LiveData<Resource<Response<List<CHydraulicProtectionEntity>>>> list(@Query("page") int page, @Query("rows") int rows, @Query("tableId") String tableName, @Query("status") String status);

    @POST("/chydraulicprotection/deleteById")
    LiveData<Resource<RespEntity>> delete(@Query("id") String id);

    @POST("/chydraulicprotection/test")
    LiveData<Resource<RespEntity>> revoke(@Body List<CHydraulicProtectionEntity> entities);

    @POST("/chydraulicprotection/test")
    LiveData<Resource<RespEntity>> revoke(@Query("id") String id);

    @POST("/chydraulicprotection/completeTaskJudge")
    LiveData<Resource<FlagRespEntity>> completeTaskJudge(@Query("id") String id, @Query("judge") String judge, @Query("status") String status, @Query("comment") String comment);

    @POST("/process/revoke")
    LiveData<Resource<FlagRespEntity>> revoked(@Query("id") String id, @Query("tableName") String tableName, @Query("status") String status);

    @POST("/util/getPhotoByName")
    LiveData<Resource<String>> getPic(@Query("fileName") String fileName);

}
