package com.spe.dcs.project.cweldjoint;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.net.FlagRespEntity;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.common.Status;
import com.spe.dcs.common.TypeStatus;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Desc:03_施工焊口
 * Author.
 * Data:
 */

public class CWeldJointRepository {

    private final com.spe.dcs.project.cweldjoint.CWeldJointDao cWeldDao;
    private final CWeldJointWebService cWeldJointWebService;
    private RequestBody body;

    @Inject
    public CWeldJointRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cWeldJointWebService = retrofit.create(CWeldJointWebService.class);
        this.cWeldDao = pcsDatabase.cWeldDao();
        Log.d("wwww", "CWeldRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CWeldJointEntity cWeldEntity, boolean isNew) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {
                try {
                    //保存失败
                    if (respEntity.getCode() != 1) {
                        return;
                    }
                    //以下是数据上传并且保存成功
//                    已审核的数据上传
                    if (TypeStatus.APP_OWNER.equals(cWeldEntity.getApproveStatus())) {
                        cWeldEntity.setStatus(Status.NORMAL);
                        cWeldDao.save(cWeldEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cWeldEntity.getApproveStatus())) {
                        cWeldEntity.setStatus(Status.NORMAL);
                        cWeldDao.save(cWeldEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cWeldEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cWeldDao.save(cWeldEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();


                Field[] fileds = cWeldEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cWeldEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cWeldJointWebService.save(params, "C_WELD");
                return cWeldJointWebService.save(params, "c_weld_joint");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CWeldJointEntity> cWeldEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CWeldJointEntity entity : cWeldEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cWeldDao.save(cWeldEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cWeldJointWebService.report(cWeldEntities.get(0).getId());
                return null;
            }
        }.getAsLiveData();
    }
    //    上报（在线网络接口）

    public LiveData<Resource<FlagRespEntity>> reports(String id) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
//                for (CWeldJointEntity entity : cWeldEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cWeldDao.save(cWeldEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cWeldJointWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CWeldJointEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cWeldDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cWeldDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cWeldJointWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CWeldJointEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CWeldJointEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldJointEntity> item) {
                for (CWeldJointEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cWeldDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CWeldJointEntity> loadFromDb() {
                return cWeldDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWeldJointEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldJointEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CWeldJointEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CWeldJointEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldJointEntity> item) {
                for (CWeldJointEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cWeldDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CWeldJointEntity> loadFromDb() {
                return cWeldDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWeldJointEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldJointEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CWeldJointEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CWeldJointEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CWeldJointEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CWeldJointEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cWeldDao.loadListSum(localStatus);
                int pages = 1;
                int fianlPage = 1;
                if (listSum % 10 == 0) {
                    pages = listSum / 10;
                } else {
                    pages = (listSum / 10) + 1;
                }
                if (page <= pages) {
                    fianlPage = (page - 1) * 10;
                } else {
                    fianlPage = 1000000000;
                    List<CWeldJointEntity> entities = cWeldDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CWeldJointEntity>> response = new Response<List<CWeldJointEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CWeldJointEntity> entities = cWeldDao.loadList(fianlPage, rows, localStatus);
                Response<List<CWeldJointEntity>> response = new Response<List<CWeldJointEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CWeldJointEntity>>>> createCall() {
                return cWeldJointWebService.list(page, rows, "C_WELD_JOINT", status);
            }
        }.getAsLiveData();
    }

    //审核与校验
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CWeldJointEntity cWeldEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cWeldDao.del(cWeldEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cWeldEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cWeldEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cWeldEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cWeldEntity.setApproveStatus(TypeStatus.OWNERS);
                    }

                    return new FlagRespEntity(true, "", cWeldDao.save(cWeldEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cWeldEntity.getJudge();

                //审核
                if ("unqualified".equals(judge)) { //不合格
//                    if (TypeStatus.OWNER.equals(owner)) { //业主抽查
//                        return cReworkWeldJointWebService.completeTaskJudge(cReworkWeldJointEntity.getId(), cReworkWeldJointEntity.getJudge(), (TypeStatus.OWNER.equals(owner) ? TypeStatus.OWNER : TypeStatus.OWNERS), cReworkWeldJointEntity.getRemark());
//                    }
//                    else {
//                        //选择不合格的时候
//                        cReworkWeldJointEntity.setApproveStatus("unqualified");
//                        return cReworkWeldJointWebService.completeTaskJudge(cReworkWeldJointEntity.getId(), cReworkWeldJointEntity.getJudge(), "unqualified", cReworkWeldJointEntity.getRemark());
//                    }
                    return cWeldJointWebService.completeTaskJudge(cWeldEntity.getId(), cWeldEntity.getJudge(), TypeStatus.ENTER, cWeldEntity.getRemark());

                }
                if(TypeStatus.OWNER.equals(owner)){
                    //抽查
                    return cWeldJointWebService.completeTaskJudge(cWeldEntity.getId(), cWeldEntity.getJudge(), (TypeStatus.ENTER), cWeldEntity.getRemark());
                } else if (TypeStatus.OWNERS.equals(owner)) {
                    //审核通过
                    return cWeldJointWebService.completeTaskJudge(cWeldEntity.getId(), cWeldEntity.getJudge(), TypeStatus.OWNERS, cWeldEntity.getRemark());
                } else {
                    //校验通过
                    return cWeldJointWebService.completeTaskJudge(cWeldEntity.getId(), cWeldEntity.getJudge(), TypeStatus.PROJECTMANAGER, cWeldEntity.getRemark());
                }

            }
        }.getAsLiveData();
    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CWeldJointEntity cWeldJointEntity) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {

            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
//                cTrussAerialCroEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cWeldDao.save(cWeldJointEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cWeldJointWebService.findUpdateId(cWeldJointEntity.getId());
            }
        }.getAsLiveData();

    }


    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CWeldJointEntity> cWeldEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CWeldJointEntity entity : cWeldEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cWeldDao.save(cWeldEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cWeldJointWebService.revoked(cWeldEntities.get(0).getId(), "C_WELD_JOINT", (type == 1 ? "enter" : "supervisor"))
                        ;
            }
        }.getAsLiveData();
    }

    //获取图片(只是在有网络情况下)
    public LiveData<Resource<String>> getPic(String path) {
        return new NetworkBoundResource<String>() {
            @Override
            protected void saveCallResult(@NonNull String respEntity) {

            }

            @NonNull
            @Override
            protected String loadFromDb() {

                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<String>> createCall() {

                return cWeldJointWebService.getPic(path);
            }
        }.getAsLiveData();

    }


    public LiveData<Resource<Response<List<CWeldJointEntity>>>> queryList(int page, int rows, String sectionNumber, String weldNum, String stakeNumber, String weldingUnit, String status) {
        return new NetworkBoundResource<Response<List<CWeldJointEntity>>>() {
            @Override
            protected void saveCallResult(@NonNull Response<List<CWeldJointEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CWeldJointEntity>> loadFromDb() {
                int listSum = cWeldDao.query2ListSum(sectionNumber, weldNum, stakeNumber, weldingUnit, status);
                int pages = 1;
                int fianlPage = 1;
                if (listSum % 10 == 0) {
                    pages = listSum / 10;
                } else {
                    pages = (listSum / 10) + 1;
                }
                if (page <= pages) {
                    fianlPage = (page - 1) * 10;
                } else {
                    fianlPage = 1000000000;
                    List<CWeldJointEntity> entities = cWeldDao.query2List(fianlPage, rows, sectionNumber, weldNum, stakeNumber, weldingUnit, status);
                    Response<List<CWeldJointEntity>> response = new Response<List<CWeldJointEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CWeldJointEntity> entities = cWeldDao.query2List(fianlPage, rows, sectionNumber, weldNum, stakeNumber, weldingUnit, status);
                Response<List<CWeldJointEntity>> response = new Response<List<CWeldJointEntity>>();
                response.setData(entities);

                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CWeldJointEntity>>>> createCall() {

                return cWeldJointWebService.querys(page, rows, "C_WELD", status, sectionNumber, weldNum, stakeNumber, weldingUnit);
            }
        }.getAsLiveData();

    }

    //     获取月新增长管线统计
    public LiveData<Resource<CWeldJointIncreaseEntity>> getIncreasePipeline(String year) {
        return new NetworkBoundResource<CWeldJointIncreaseEntity>() {
            @Override
            protected void saveCallResult(@NonNull CWeldJointIncreaseEntity item) {
            }

            @NonNull
            @Override
            protected CWeldJointIncreaseEntity loadFromDb() {

                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<CWeldJointIncreaseEntity>> createCall() {
                return cWeldJointWebService.getIncreasePipeline(year);
            }
        }.getAsLiveData();
    }

    //     获取管道焊接累计长度统计
    public LiveData<Resource<List<CWeldJointLengthEntity>>> getAllLength() {
        return new NetworkBoundResource<List<CWeldJointLengthEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldJointLengthEntity> item) {
            }

            @NonNull
            @Override
            protected List<CWeldJointLengthEntity> loadFromDb() {

                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldJointLengthEntity>>> createCall() {
                return cWeldJointWebService.getAllLength();
            }
        }.getAsLiveData();
    }

    //     获取焊接情况统计
    public LiveData<Resource<List<CWeldJointConditionEntity>>> getWeldStatistic(String startMonth, String endMonth) {
        return new NetworkBoundResource<List<CWeldJointConditionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldJointConditionEntity> item) {
            }

            @NonNull
            @Override
            protected List<CWeldJointConditionEntity> loadFromDb() {

                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldJointConditionEntity>>> createCall() {
                return cWeldJointWebService.getWeldStatistic(startMonth, endMonth);
            }
        }.getAsLiveData();
    }

    //     获取录入，上报，审核数据统计
    public LiveData<Resource<List<CWeldJointCollectionEntity>>> getDataCollection(String startMonth, String endMonth) {
        return new NetworkBoundResource<List<CWeldJointCollectionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWeldJointCollectionEntity> item) {
            }

            @NonNull
            @Override
            protected List<CWeldJointCollectionEntity> loadFromDb() {

                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWeldJointCollectionEntity>>> createCall() {
                return cWeldJointWebService.getDataCollection(startMonth, endMonth);
            }
        }.getAsLiveData();
    }

}
