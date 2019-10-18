package com.spe.dcs.project.cjointcoating;

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
 * Desc:06_防腐补口
 * Author.
 * Data:
 */

public class CJointCoatingRepository {

    private final CJointCoatingDao cAntiCoatingDao;
    private final CJointCoatingWebService cAntiCoatingWebService;
    private RequestBody body;

    @Inject
    public CJointCoatingRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cAntiCoatingWebService = retrofit.create(CJointCoatingWebService.class);
        this.cAntiCoatingDao = pcsDatabase.cJointCoatingDao();
        Log.d("wwww", "CJointCoatingRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CJointCoatingEntity cAntiCoatingEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cAntiCoatingEntity.getApproveStatus())) {
                        cAntiCoatingEntity.setStatus(Status.NORMAL);
                        cAntiCoatingDao.save(cAntiCoatingEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cAntiCoatingEntity.getApproveStatus())) {
                        cAntiCoatingEntity.setStatus(Status.NORMAL);
                        cAntiCoatingDao.save(cAntiCoatingEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cAntiCoatingEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cAntiCoatingDao.save(cAntiCoatingEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();


                if (isNew) {

                } else {
                    body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    params.put("photoPaths\";filename=\"" + "", body);

                }
                Field[] fileds = cAntiCoatingEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cAntiCoatingEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cAntiCoatingWebService.save(params, "C_ANTI_COATING");
                return cAntiCoatingWebService.save(params, "c_anti_coating");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CJointCoatingEntity> cAntiCoatingEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CJointCoatingEntity entity : cAntiCoatingEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cAntiCoatingDao.save(cAntiCoatingEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cAntiCoatingWebService.report(cAntiCoatingEntities.get(0).getId());
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
//                for (CAntiCoatingEntity entity : cAntiCoatingEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cAntiCoatingDao.save(cAntiCoatingEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cAntiCoatingWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CJointCoatingEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cAntiCoatingDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cAntiCoatingDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cAntiCoatingWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CJointCoatingEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CJointCoatingEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CJointCoatingEntity> item) {
                for (CJointCoatingEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cAntiCoatingDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CJointCoatingEntity> loadFromDb() {
                return cAntiCoatingDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CJointCoatingEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CJointCoatingEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CJointCoatingEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CJointCoatingEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CJointCoatingEntity> item) {
                for (CJointCoatingEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cAntiCoatingDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CJointCoatingEntity> loadFromDb() {
                return cAntiCoatingDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CJointCoatingEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CJointCoatingEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CJointCoatingEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CJointCoatingEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CJointCoatingEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CJointCoatingEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cAntiCoatingDao.loadListSum(localStatus);
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
                    List<CJointCoatingEntity> entities = cAntiCoatingDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CJointCoatingEntity>> response = new Response<List<CJointCoatingEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CJointCoatingEntity> entities = cAntiCoatingDao.loadList(fianlPage, rows, localStatus);
                Response<List<CJointCoatingEntity>> response = new Response<List<CJointCoatingEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CJointCoatingEntity>>>> createCall() {
                return cAntiCoatingWebService.list(page, rows, "C_JOINT_COATING", status);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CJointCoatingEntity cAntiCoatingEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cAntiCoatingDao.del(cAntiCoatingEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cAntiCoatingEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cAntiCoatingEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cAntiCoatingEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cAntiCoatingEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cAntiCoatingDao.save(cAntiCoatingEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cAntiCoatingEntity.getJudge();

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
                    return cAntiCoatingWebService.completeTaskJudge(cAntiCoatingEntity.getId(), cAntiCoatingEntity.getJudge(), TypeStatus.ENTER, cAntiCoatingEntity.getRemark());

                }
                if(TypeStatus.OWNER.equals(owner)){
                    //抽查
                    return cAntiCoatingWebService.completeTaskJudge(cAntiCoatingEntity.getId(), cAntiCoatingEntity.getJudge(), (TypeStatus.ENTER), cAntiCoatingEntity.getRemark());
                } else if (TypeStatus.OWNERS.equals(owner)) {
                    //审核通过
                    return cAntiCoatingWebService.completeTaskJudge(cAntiCoatingEntity.getId(), cAntiCoatingEntity.getJudge(), TypeStatus.OWNERS, cAntiCoatingEntity.getRemark());
                } else {
                    //校验通过
                    return cAntiCoatingWebService.completeTaskJudge(cAntiCoatingEntity.getId(), cAntiCoatingEntity.getJudge(), TypeStatus.PROJECTMANAGER, cAntiCoatingEntity.getRemark());
                }

            }
        }.getAsLiveData();
    }

    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CJointCoatingEntity cJointCoatingEntity) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {

            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
//                cTrussAerialCroEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cAntiCoatingDao.save(cJointCoatingEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cAntiCoatingWebService.findUpdateId(cJointCoatingEntity.getId());
            }
        }.getAsLiveData();

    }

    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CJointCoatingEntity> cAntiCoatingEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CJointCoatingEntity entity : cAntiCoatingEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cAntiCoatingDao.save(cAntiCoatingEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cAntiCoatingWebService.revoked(cAntiCoatingEntities.get(0).getId(), "C_JOINT_COATING", (type == 1 ? "enter" : "supervisor"))
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

                return cAntiCoatingWebService.getPic(path);
            }
        }.getAsLiveData();

    }

}
