package com.spe.dcs.project.ccoatingrepair;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.net.FlagRespEntity;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.common.Status;
import com.spe.dcs.common.TypeStatus;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Desc:09_施工防腐补伤
 * Author.
 * Data:
 */

public class CCoatingRepairRepository {

    private final CCoatingRepairDao cAntiCoatingRepairDao;
    private final CCoatingRepairWebService cCoatingRepairWebService;
    private RequestBody body;

    @Inject
    public CCoatingRepairRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cCoatingRepairWebService = retrofit.create(CCoatingRepairWebService.class);
        this.cAntiCoatingRepairDao = pcsDatabase.cAntiCoatingRepairDao();
        Log.d("wwww", "CAntiCoatingRepairRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CCoatingRepairEntity cAntiCoatingRepairEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cAntiCoatingRepairEntity.getApproveStatus())) {
                        cAntiCoatingRepairEntity.setStatus(Status.NORMAL);
                        cAntiCoatingRepairDao.save(cAntiCoatingRepairEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cAntiCoatingRepairEntity.getApproveStatus())) {
                        cAntiCoatingRepairEntity.setStatus(Status.NORMAL);
                        cAntiCoatingRepairDao.save(cAntiCoatingRepairEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cAntiCoatingRepairEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cAntiCoatingRepairDao.save(cAntiCoatingRepairEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();

                Field[] fileds = cAntiCoatingRepairEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cAntiCoatingRepairEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cCoatingRepairWebService.save(params, "C_ANTI_COATING_REPAIR");
                return cCoatingRepairWebService.save(params, "c_anti_coating_repair");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CCoatingRepairEntity> cAntiCoatingRepairEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CCoatingRepairEntity entity : cAntiCoatingRepairEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cAntiCoatingRepairDao.save(cAntiCoatingRepairEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cCoatingRepairWebService.report(cAntiCoatingRepairEntities.get(0).getId());
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
//                for (CAntiCoatingRepairEntity entity : cAntiCoatingRepairEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cAntiCoatingRepairDao.save(cAntiCoatingRepairEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cCoatingRepairWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CCoatingRepairEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cAntiCoatingRepairDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cAntiCoatingRepairDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cCoatingRepairWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CCoatingRepairEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CCoatingRepairEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CCoatingRepairEntity> item) {
                for (CCoatingRepairEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cAntiCoatingRepairDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CCoatingRepairEntity> loadFromDb() {
                return cAntiCoatingRepairDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CCoatingRepairEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CCoatingRepairEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CCoatingRepairEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CCoatingRepairEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CCoatingRepairEntity> item) {
                for (CCoatingRepairEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cAntiCoatingRepairDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CCoatingRepairEntity> loadFromDb() {
                return cAntiCoatingRepairDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CCoatingRepairEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CCoatingRepairEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CCoatingRepairEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CCoatingRepairEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CCoatingRepairEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CCoatingRepairEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cAntiCoatingRepairDao.loadListSum(localStatus);
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
                    List<CCoatingRepairEntity> entities = cAntiCoatingRepairDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CCoatingRepairEntity>> response = new Response<List<CCoatingRepairEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CCoatingRepairEntity> entities = cAntiCoatingRepairDao.loadList(fianlPage, rows, localStatus);
                Response<List<CCoatingRepairEntity>> response = new Response<List<CCoatingRepairEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CCoatingRepairEntity>>>> createCall() {
                return cCoatingRepairWebService.list(page, rows, "C_COATING_REPAIR", status);
            }
        }.getAsLiveData();
    }
//审核与校验
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CCoatingRepairEntity cAntiCoatingRepairEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cAntiCoatingRepairDao.del(cAntiCoatingRepairEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cAntiCoatingRepairEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cAntiCoatingRepairEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cAntiCoatingRepairEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cAntiCoatingRepairEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cAntiCoatingRepairDao.save(cAntiCoatingRepairEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cAntiCoatingRepairEntity.getJudge();

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
                    return cCoatingRepairWebService.completeTaskJudge(cAntiCoatingRepairEntity.getId(), cAntiCoatingRepairEntity.getJudge(), TypeStatus.ENTER, cAntiCoatingRepairEntity.getRemark());

                }
                if(TypeStatus.OWNER.equals(owner)){
                    //抽查
                    return cCoatingRepairWebService.completeTaskJudge(cAntiCoatingRepairEntity.getId(), cAntiCoatingRepairEntity.getJudge(), (TypeStatus.ENTER), cAntiCoatingRepairEntity.getRemark());
                } else if (TypeStatus.OWNERS.equals(owner)) {
                    //审核通过
                    return cCoatingRepairWebService.completeTaskJudge(cAntiCoatingRepairEntity.getId(), cAntiCoatingRepairEntity.getJudge(), TypeStatus.OWNERS, cAntiCoatingRepairEntity.getRemark());
                } else {
                    //校验通过
                    return cCoatingRepairWebService.completeTaskJudge(cAntiCoatingRepairEntity.getId(), cAntiCoatingRepairEntity.getJudge(), TypeStatus.PROJECTMANAGER, cAntiCoatingRepairEntity.getRemark());
                }

            }
        }.getAsLiveData();
    }

    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CCoatingRepairEntity cCoatingRepairEntity) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {

            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
//                cTrussAerialCroEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cAntiCoatingRepairDao.save(cCoatingRepairEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cCoatingRepairWebService.findUpdateId(cCoatingRepairEntity.getId());
            }
        }.getAsLiveData();

    }
    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CCoatingRepairEntity> cAntiCoatingRepairEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CCoatingRepairEntity entity : cAntiCoatingRepairEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cAntiCoatingRepairDao.save(cAntiCoatingRepairEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cCoatingRepairWebService.revoked(cAntiCoatingRepairEntities.get(0).getId(), "C_COATING_REPAIR", (type == 1 ? "enter" : "supervisor"))
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

                return cCoatingRepairWebService.getPic(path);
            }
        }.getAsLiveData();

    }

}
