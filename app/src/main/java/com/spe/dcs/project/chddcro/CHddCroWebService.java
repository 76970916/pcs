package com.spe.dcs.project.chddcro;

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
 * Desc:41_施工定向钻穿越
 * Author.
 * Data:
 */
public interface CHddCroWebService {
    @Multipart
    @POST("/chddcro/save")
    LiveData<Resource<RespEntity>> save(@PartMap Map<String, RequestBody> params, @Query("input_hidden_tablename") String tableName);


    @POST("/chddcro/dataReport")
    LiveData<Resource<FlagRespEntity>> report(@Query("id") String id);

    @POST("/chddcro/getDataListByStatus")
    LiveData<Resource<Response<List<CHddCroEntity>>>> list(@Query("page") int page, @Query("rows") int rows, @Query("tableId") String tableName, @Query("status") String status);

    @POST("/chddcro/deleteById")
    LiveData<Resource<RespEntity>> delete(@Query("id") String id);

    @POST("/chddcro/test")
    LiveData<Resource<RespEntity>> revoke(@Body List<CHddCroEntity> entities);

    @POST("/chddcro/test")
    LiveData<Resource<RespEntity>> revoke(@Query("id") String id);

    @POST("/chddcro/completeTaskJudge")
    LiveData<Resource<FlagRespEntity>> completeTaskJudge(@Query("id") String id, @Query("judge") String judge, @Query("status") String status, @Query("comment") String comment);

    @POST("/process/revoke")
    LiveData<Resource<FlagRespEntity>> revoked(@Query("id") String id, @Query("tableName") String tableName, @Query("status") String status);

    @POST("/util/getPhotoByName")
    LiveData<Resource<String>> getPic(@Query("fileName") String fileName);

    @POST("/chddcro/findByIdApp")
    LiveData<Resource<RespEntity>> findUpdateId(@Query("id") String id);

}
