package com.spe.dcs.project.cweldjoint;

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
 * Desc:03_施工焊口
 * Author.
 * Data:
 */
public interface CWeldJointWebService {
    @Multipart
    @POST("/cweldjoint/save")
    LiveData<Resource<RespEntity>> save(@PartMap Map<String, RequestBody> params, @Query("input_hidden_tablename") String tableName);

    @POST("/cweldjoint/dataReport")
    LiveData<Resource<FlagRespEntity>> report(@Query("id") String id);

    @POST("/cweldjoint/getDataListByStatus")
    LiveData<Resource<Response<List<CWeldJointEntity>>>> list(@Query("page") int page, @Query("rows") int rows, @Query("tableId") String tableName, @Query("status") String status);

    @POST("/cweldjoint/deleteById")
    LiveData<Resource<RespEntity>> delete(@Query("id") String id);

    @POST("/cweldjoint/test")
    LiveData<Resource<RespEntity>> revoke(@Body List<CWeldJointEntity> entities);

    @POST("/cweldjoint/test")
    LiveData<Resource<RespEntity>> revoke(@Query("id") String id);

    @POST("/cweldjoint/completeTaskJudge")
    LiveData<Resource<FlagRespEntity>> completeTaskJudge(@Query("id") String id, @Query("judge") String judge, @Query("status") String status, @Query("comment") String comment);

    @POST("/process/revoke")
    LiveData<Resource<FlagRespEntity>> revoked(@Query("id") String id, @Query("tableName") String tableName, @Query("status") String status);

    @POST("/util/getPhotoByName")
    LiveData<Resource<String>> getPic(@Query("fileName") String fileName);

    @POST("/cweldjoint/getDataListByStatus")
    LiveData<Resource<Response<List<CWeldJointEntity>>>> querys(@Query("page") int page, @Query("rows") int rows,
                                                                @Query("tableId") String tableName, @Query("status") String status,
                                                                @Query("section") String sectionNumber, @Query("weldJointNum") String weldJointNum,
                                                                @Query("stakeNumber") String stakeNumber, @Query("weldingUnitId") String weldingUnitId);
    @POST("/cweldjoint/monthAddWeld")
    LiveData<Resource<CWeldJointIncreaseEntity>> getIncreasePipeline(@Query("year") String year);

    @POST("/cweldjoint/pipeWeldLength")
    LiveData<Resource<List<CWeldJointLengthEntity>>> getAllLength();

    @POST("/cweldjoint/weldStatistics")
    LiveData<Resource<List<CWeldJointConditionEntity>>> getWeldStatistic(@Query("sym") String startMonth, @Query("eym") String endMonth);

    @POST("/cweldjoint/dataCollectionStatistics")
    LiveData<Resource<List<CWeldJointCollectionEntity>>> getDataCollection(@Query("sym") String startMonth, @Query("eym") String endMonth);
    @POST("/cweldjoint/findByIdApp")
    LiveData<Resource<RespEntity>> findUpdateId(@Query("id") String id);

}
