package com.spe.dcs.project.chydraulicprotection;

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
 * Desc:25_施工水工保护
 * Author.
 * Data:
 */

public class CHydraulicProtectionRepository {

    private final CHydraulicProtectionDao cHydraulicProtectionDao;
    private final CHydraulicProtectionWebService cHydraulicProtectionWebService;
    private RequestBody body;

    @Inject
    public CHydraulicProtectionRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cHydraulicProtectionWebService = retrofit.create(CHydraulicProtectionWebService.class);
        this.cHydraulicProtectionDao = pcsDatabase.cHydraulicProtectionDao();
        Log.d("wwww", "CHydraulicProtectionRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CHydraulicProtectionEntity cHydraulicProtectionEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cHydraulicProtectionEntity.getApproveStatus())) {
                        cHydraulicProtectionEntity.setStatus(Status.NORMAL);
                        cHydraulicProtectionDao.save(cHydraulicProtectionEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cHydraulicProtectionEntity.getApproveStatus())) {
                        cHydraulicProtectionEntity.setStatus(Status.NORMAL);
                        cHydraulicProtectionDao.save(cHydraulicProtectionEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cHydraulicProtectionEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cHydraulicProtectionDao.save(cHydraulicProtectionEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();
                String photoPath = cHydraulicProtectionEntity.getPhotoPath();
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
                    cHydraulicProtectionEntity.setPhotoPath("");
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
                        cHydraulicProtectionEntity.setPhotoPath(json);
                    } else {
                        cHydraulicProtectionEntity.setPhotoPath(photoPath);
                    }

                }


                Field[] fileds = cHydraulicProtectionEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cHydraulicProtectionEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

                return cHydraulicProtectionWebService.save(params, "C_HYDRAULIC_PROTECTION");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CHydraulicProtectionEntity> cHydraulicProtectionEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CHydraulicProtectionEntity entity : cHydraulicProtectionEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cHydraulicProtectionDao.save(cHydraulicProtectionEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cHydraulicProtectionWebService.report(cHydraulicProtectionEntities.get(0).getId());
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
//                for (CHydraulicProtectionEntity entity : cHydraulicProtectionEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cHydraulicProtectionDao.save(cHydraulicProtectionEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cHydraulicProtectionWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CHydraulicProtectionEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cHydraulicProtectionDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cHydraulicProtectionDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cHydraulicProtectionWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CHydraulicProtectionEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CHydraulicProtectionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CHydraulicProtectionEntity> item) {
                for (CHydraulicProtectionEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cHydraulicProtectionDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CHydraulicProtectionEntity> loadFromDb() {
                return cHydraulicProtectionDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CHydraulicProtectionEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CHydraulicProtectionEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CHydraulicProtectionEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CHydraulicProtectionEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CHydraulicProtectionEntity> item) {
                for (CHydraulicProtectionEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cHydraulicProtectionDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CHydraulicProtectionEntity> loadFromDb() {
                return cHydraulicProtectionDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CHydraulicProtectionEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CHydraulicProtectionEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CHydraulicProtectionEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CHydraulicProtectionEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CHydraulicProtectionEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CHydraulicProtectionEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cHydraulicProtectionDao.loadListSum(localStatus);
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
                    List<CHydraulicProtectionEntity> entities = cHydraulicProtectionDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CHydraulicProtectionEntity>> response = new Response<List<CHydraulicProtectionEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CHydraulicProtectionEntity> entities = cHydraulicProtectionDao.loadList(fianlPage, rows, localStatus);
                Response<List<CHydraulicProtectionEntity>> response = new Response<List<CHydraulicProtectionEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CHydraulicProtectionEntity>>>> createCall() {
                return cHydraulicProtectionWebService.list(page, rows, "C_HYDRAULIC_PROTECTION", status);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen,CHydraulicProtectionEntity cHydraulicProtectionEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cHydraulicProtectionDao.del(cHydraulicProtectionEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cHydraulicProtectionEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cHydraulicProtectionEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cHydraulicProtectionEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cHydraulicProtectionEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cHydraulicProtectionDao.save(cHydraulicProtectionEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cHydraulicProtectionEntity.getJudge();
                if ("unqualified".equals(judge)) { //不合格
                    if (TypeStatus.PROJECTMANAGER.equals(owner)) { //业主抽查
                        return cHydraulicProtectionWebService.completeTaskJudge(cHydraulicProtectionEntity.getId(), cHydraulicProtectionEntity.getJudge(), (TypeStatus.PROJECTMANAGER.equals(owner) ? TypeStatus.ENTER : TypeStatus.OWNERS), cHydraulicProtectionEntity.getRemark());
                    }
                    //选择不合格的时候
                    cHydraulicProtectionEntity.setApproveStatus(TypeStatus.ENTER);
                    return cHydraulicProtectionWebService.completeTaskJudge(cHydraulicProtectionEntity.getId(), cHydraulicProtectionEntity.getJudge(), TypeStatus.ENTER, cHydraulicProtectionEntity.getRemark());
                }
                if(isShen){//如果是审核的话
                    return cHydraulicProtectionWebService.completeTaskJudge(cHydraulicProtectionEntity.getId(), cHydraulicProtectionEntity.getJudge(),TypeStatus.OWNERS, cHydraulicProtectionEntity.getRemark());
                }else {//如果是校验的话
                    return cHydraulicProtectionWebService.completeTaskJudge(cHydraulicProtectionEntity.getId(), cHydraulicProtectionEntity.getJudge(), TypeStatus.PROJECTMANAGER, cHydraulicProtectionEntity.getRemark());

                }
            }
        }.getAsLiveData();
    }


    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CHydraulicProtectionEntity> cHydraulicProtectionEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CHydraulicProtectionEntity entity : cHydraulicProtectionEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cHydraulicProtectionDao.save(cHydraulicProtectionEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cHydraulicProtectionWebService.revoked(cHydraulicProtectionEntities.get(0).getId(), "C_HYDRAULIC_PROTECTION", (type == 1 ? "enter" : "supervisor"))
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

                return cHydraulicProtectionWebService.getPic(path);
            }
        }.getAsLiveData();

    }

}
