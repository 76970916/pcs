package com.spe.dcs.project.creworkweldjoint;

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
 * Desc:04_施工返修口
 * Author.
 * Data:
 */

public class CReworkWeldJointRepository {

    private final CReworkWeldJointDao cReworkWeldJointDao;
    private final CReworkWeldJointWebService cReworkWeldJointWebService;
    private RequestBody body;

    @Inject
    public CReworkWeldJointRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cReworkWeldJointWebService = retrofit.create(CReworkWeldJointWebService.class);
        this.cReworkWeldJointDao = pcsDatabase.cReworkWeldJointDao();
        Log.d("wwww", "CWeldReworkRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CReworkWeldJointEntity cReworkWeldJointEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cReworkWeldJointEntity.getApproveStatus())) {
                        cReworkWeldJointEntity.setStatus(Status.NORMAL);
                        cReworkWeldJointDao.save(cReworkWeldJointEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cReworkWeldJointEntity.getApproveStatus())) {
                        cReworkWeldJointEntity.setStatus(Status.NORMAL);
                        cReworkWeldJointDao.save(cReworkWeldJointEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cReworkWeldJointEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cReworkWeldJointDao.save(cReworkWeldJointEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();

                Field[] fileds = cReworkWeldJointEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cReworkWeldJointEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cReworkWeldJointWebService.save(params, "C_WELD_REWORK");
                return cReworkWeldJointWebService.save(params, "c_weld_rework");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CReworkWeldJointEntity> cWeldReworkEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CReworkWeldJointEntity entity : cWeldReworkEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cReworkWeldJointDao.save(cWeldReworkEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cReworkWeldJointWebService.report(cWeldReworkEntities.get(0).getId());
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
//                for (CReworkWeldJointEntity entity : cWeldReworkEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cReworkWeldJointDao.save(cWeldReworkEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cReworkWeldJointWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CReworkWeldJointEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cReworkWeldJointDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cReworkWeldJointDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cReworkWeldJointWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CReworkWeldJointEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CReworkWeldJointEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CReworkWeldJointEntity> item) {
                for (CReworkWeldJointEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cReworkWeldJointDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CReworkWeldJointEntity> loadFromDb() {
                return cReworkWeldJointDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CReworkWeldJointEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CReworkWeldJointEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CReworkWeldJointEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CReworkWeldJointEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CReworkWeldJointEntity> item) {
                for (CReworkWeldJointEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cReworkWeldJointDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CReworkWeldJointEntity> loadFromDb() {
                return cReworkWeldJointDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CReworkWeldJointEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CReworkWeldJointEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CReworkWeldJointEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CReworkWeldJointEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CReworkWeldJointEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CReworkWeldJointEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cReworkWeldJointDao.loadListSum(localStatus);
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
                    List<CReworkWeldJointEntity> entities = cReworkWeldJointDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CReworkWeldJointEntity>> response = new Response<List<CReworkWeldJointEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CReworkWeldJointEntity> entities = cReworkWeldJointDao.loadList(fianlPage, rows, localStatus);
                Response<List<CReworkWeldJointEntity>> response = new Response<List<CReworkWeldJointEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CReworkWeldJointEntity>>>> createCall() {
                return cReworkWeldJointWebService.list(page, rows, "C_REWORK_WELD_JOINT", status);
            }
        }.getAsLiveData();
    }

    //审核与校验
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CReworkWeldJointEntity cReworkWeldJointEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cReworkWeldJointDao.del(cReworkWeldJointEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cReworkWeldJointEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cReworkWeldJointEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cReworkWeldJointEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cReworkWeldJointEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cReworkWeldJointDao.save(cReworkWeldJointEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cReworkWeldJointEntity.getJudge();

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
                    return cReworkWeldJointWebService.completeTaskJudge(cReworkWeldJointEntity.getId(), cReworkWeldJointEntity.getJudge(), TypeStatus.ENTER, cReworkWeldJointEntity.getRemark());

                }
                   if(TypeStatus.OWNER.equals(owner)){
                       //抽查
                       return cReworkWeldJointWebService.completeTaskJudge(cReworkWeldJointEntity.getId(), cReworkWeldJointEntity.getJudge(), (TypeStatus.ENTER), cReworkWeldJointEntity.getRemark());
                   } else if (TypeStatus.OWNERS.equals(owner)) {
                       //审核通过
                       return cReworkWeldJointWebService.completeTaskJudge(cReworkWeldJointEntity.getId(), cReworkWeldJointEntity.getJudge(), TypeStatus.OWNERS, cReworkWeldJointEntity.getRemark());
                   } else {
                       //校验通过
                       return cReworkWeldJointWebService.completeTaskJudge(cReworkWeldJointEntity.getId(), cReworkWeldJointEntity.getJudge(), TypeStatus.PROJECTMANAGER, cReworkWeldJointEntity.getRemark());
                   }

            }
        }.getAsLiveData();
    }

    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CReworkWeldJointEntity cReworkWeldJointEntity) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {

            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
//                cTrussAerialCroEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cReworkWeldJointDao.save(cReworkWeldJointEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cReworkWeldJointWebService.findUpdateId(cReworkWeldJointEntity.getId());
            }
        }.getAsLiveData();

    }

    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CReworkWeldJointEntity> cWeldReworkEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CReworkWeldJointEntity entity : cWeldReworkEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cReworkWeldJointDao.save(cWeldReworkEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cReworkWeldJointWebService.revoked(cWeldReworkEntities.get(0).getId(), "C_REWORK_WELD_JOINT", (type == 1 ? "enter" : "supervisor"))
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

                return cReworkWeldJointWebService.getPic(path);
            }
        }.getAsLiveData();

    }

}
