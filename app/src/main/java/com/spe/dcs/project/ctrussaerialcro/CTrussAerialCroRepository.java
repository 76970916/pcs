package com.spe.dcs.project.ctrussaerialcro;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import android.text.TextUtils;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Desc:桁架跨越
 * Author.
 * Data:
 */

public class CTrussAerialCroRepository {

    private final CTrussAerialCroDao cTrussAerialCroDao;
    private final CTrussAerialCroWebService cTrussAerialCroWebService;
    private RequestBody body;

    @Inject
    public CTrussAerialCroRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cTrussAerialCroWebService = retrofit.create(CTrussAerialCroWebService.class);
        this.cTrussAerialCroDao = pcsDatabase.cTrussAerialCroDao();
        Log.d("wwww", "CBoxculvertCrossRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CTrussAerialCroEntity cTrussAerialCroEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cTrussAerialCroEntity.getApproveStatus())) {
                        cTrussAerialCroEntity.setStatus(Status.NORMAL);
                        cTrussAerialCroDao.save(cTrussAerialCroEntity);
                    }
//                    已上报的数据上传

                    if (TypeStatus.APP_SUPERVISOR.equals(cTrussAerialCroEntity.getApproveStatus())) {
                        cTrussAerialCroEntity.setStatus(Status.NORMAL);
                        cTrussAerialCroDao.save(cTrussAerialCroEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cTrussAerialCroEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cTrussAerialCroDao.save(cTrussAerialCroEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();
                String photoPath = cTrussAerialCroEntity.getPhotoPath();
                if (isNew) {
                    if (!TextUtils.isEmpty(photoPath)) {
                        GsonBuilder builder = new GsonBuilder();
                        builder.enableComplexMapKeySerialization();
                        Gson gson = builder.create();
                        ArrayList<String> list = gson.fromJson(photoPath, new TypeToken<ArrayList<String>>() {
                        }.getType());
                        if (list.size() != 0) {
                            for (int i = 0; i < list.size(); i++) {
                                File file = new File(list.get(i));
                                //上传图片时候写
                                body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                params.put("photoPaths\";filename=\"" + file.getName(), body);//上传图片时候写
                            }
                        }
                    } else {
                        body = RequestBody.create(MediaType.parse("multipart/form-data"), "");//未上传图片时候写
                        params.put("photoPaths\";filename=\"" + "", body); //未上传图片时候写
                    }
                    cTrussAerialCroEntity.setPhotoPath("");
                } else {
                    body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                    params.put("photoPaths\";filename=\"" + "", body);
                    if (!TextUtils.isEmpty(photoPath)) {
                        String[] split = photoPath.split(";");
                        List<String> stringList = Arrays.asList(split);
                        GsonBuilder builder = new GsonBuilder();
                        builder.enableComplexMapKeySerialization();
                        Gson gson = builder.create();
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.addAll(stringList);
                        String json = gson.toJson(arrayList);
                        cTrussAerialCroEntity.setPhotoPath(json);
                    } else {
                        cTrussAerialCroEntity.setPhotoPath(photoPath);
                    }

                }

                Field[] fileds = cTrussAerialCroEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cTrussAerialCroEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cTrussAerialCroWebService.save(params, "C_TRUSS_AERIAL_CRO");
                return cTrussAerialCroWebService.save(params, "C_TRUSS_AERIAL_CRO");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CTrussAerialCroEntity> cBoxculvertCrossEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CTrussAerialCroEntity entity : cBoxculvertCrossEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    entity.setApproveStatus(TypeStatus.SUPERVISOR);
//              保存在数据库中
                    cTrussAerialCroDao.save(cBoxculvertCrossEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cTrussAerialCroWebService.report(cBoxculvertCrossEntities.get(0).getId());
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
//                for (CTrussAerialCroEntity entity : cBoxculvertCrossEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cTrussAerialCroDao.save(cBoxculvertCrossEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cTrussAerialCroWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CTrussAerialCroEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cTrussAerialCroDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cTrussAerialCroDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cTrussAerialCroWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CTrussAerialCroEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CTrussAerialCroEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CTrussAerialCroEntity> item) {
                for (CTrussAerialCroEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cTrussAerialCroDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CTrussAerialCroEntity> loadFromDb() {
                return cTrussAerialCroDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CTrussAerialCroEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CTrussAerialCroEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CTrussAerialCroEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CTrussAerialCroEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CTrussAerialCroEntity> item) {
                for (CTrussAerialCroEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cTrussAerialCroDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CTrussAerialCroEntity> loadFromDb() {
                return cTrussAerialCroDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CTrussAerialCroEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CTrussAerialCroEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CTrussAerialCroEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CTrussAerialCroEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CTrussAerialCroEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CTrussAerialCroEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cTrussAerialCroDao.loadListSum(localStatus);
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
                    List<CTrussAerialCroEntity> entities = cTrussAerialCroDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CTrussAerialCroEntity>> response = new Response<List<CTrussAerialCroEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CTrussAerialCroEntity> entities = cTrussAerialCroDao.loadList(fianlPage, rows, localStatus);
                Response<List<CTrussAerialCroEntity>> response = new Response<List<CTrussAerialCroEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CTrussAerialCroEntity>>>> createCall() {
                return cTrussAerialCroWebService.list(page, rows, "C_TRUSS_AERIAL_CRO", status);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen, CTrussAerialCroEntity cTrussAerialCroEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cTrussAerialCroDao.del(cTrussAerialCroEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cTrussAerialCroEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cTrussAerialCroEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cTrussAerialCroEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cTrussAerialCroEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cTrussAerialCroDao.save(cTrussAerialCroEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cTrussAerialCroEntity.getJudge();
                if ("unqualified".equals(judge)) { //不合格
                    if (TypeStatus.PROJECTMANAGER.equals(owner)) { //业主抽查
                        return cTrussAerialCroWebService.completeTaskJudge(cTrussAerialCroEntity.getId(), cTrussAerialCroEntity.getJudge(), (TypeStatus.PROJECTMANAGER.equals(owner) ? TypeStatus.ENTER : TypeStatus.OWNERS), cTrussAerialCroEntity.getRemark());
                    }
                    //选择不合格的时候
                    cTrussAerialCroEntity.setApproveStatus(TypeStatus.ENTER);
                    return cTrussAerialCroWebService.completeTaskJudge(cTrussAerialCroEntity.getId(), cTrussAerialCroEntity.getJudge(), TypeStatus.ENTER, cTrussAerialCroEntity.getRemark());
                }
                if (isShen) { //如果是审核的话
                    return cTrussAerialCroWebService.completeTaskJudge(cTrussAerialCroEntity.getId(), cTrussAerialCroEntity.getJudge(), TypeStatus.OWNERS, cTrussAerialCroEntity.getRemark());
                } else {//如果是校验的话
                    return cTrussAerialCroWebService.completeTaskJudge(cTrussAerialCroEntity.getId(), cTrussAerialCroEntity.getJudge(), TypeStatus.PROJECTMANAGER, cTrussAerialCroEntity.getRemark());

                }

            }
        }.getAsLiveData();
    }


    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CTrussAerialCroEntity> cBoxculvertCrossEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CTrussAerialCroEntity entity : cBoxculvertCrossEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cTrussAerialCroDao.save(cBoxculvertCrossEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cTrussAerialCroWebService.revoked(cBoxculvertCrossEntities.get(0).getId(), "C_TRUSS_AERIAL_CRO", (type == 1 ? "enter" : "supervisor"))
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

                return cTrussAerialCroWebService.getPic(path);
            }
        }.getAsLiveData();

    }


    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CTrussAerialCroEntity cTrussAerialCroEntity) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {

            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
//                cTrussAerialCroEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cTrussAerialCroDao.save(cTrussAerialCroEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cTrussAerialCroWebService.findUpdateId(cTrussAerialCroEntity.getId());
            }
        }.getAsLiveData();

    }


}
