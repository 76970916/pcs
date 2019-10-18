package com.spe.dcs.project.cmarkstake;

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
 * Desc:29_施工标志桩
 * Author.
 * Data:
 */

public class CMarkStakeRepository {

    private final CMarkStakeDao cMarkStakeDao;
    private final CMarkStakeWebService cMarkStakeWebService;
    private RequestBody body;

    @Inject
    public CMarkStakeRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cMarkStakeWebService = retrofit.create(CMarkStakeWebService.class);
        this.cMarkStakeDao = pcsDatabase.cMarkStakeDao();
        Log.d("wwww", "CMarkStakeRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CMarkStakeEntity cMarkStakeEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cMarkStakeEntity.getApproveStatus())) {
                        cMarkStakeEntity.setStatus(Status.NORMAL);
                        cMarkStakeDao.save(cMarkStakeEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cMarkStakeEntity.getApproveStatus())) {
                        cMarkStakeEntity.setStatus(Status.NORMAL);
                        cMarkStakeDao.save(cMarkStakeEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cMarkStakeEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cMarkStakeDao.save(cMarkStakeEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();
                String photoPath = cMarkStakeEntity.getPhotoPath();
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
                    cMarkStakeEntity.setPhotoPath("");
                } else {
                    body = RequestBody.create(MediaType.parse("multipart/form-data"), "");//未上传图片时候写
                    params.put("photoPaths\";filename=\"" + "", body); //未上传图片时候写
                    if (!TextUtils.isEmpty(photoPath)) {
                        String[] split = photoPath.split(";");
                        List<String> stringList = Arrays.asList(split);
                        GsonBuilder builder = new GsonBuilder();
                        builder.enableComplexMapKeySerialization();
                        Gson gson = builder.create();
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.addAll(stringList);
                        String json = gson.toJson(arrayList);
                        cMarkStakeEntity.setPhotoPath(json);
                    } else {
                        cMarkStakeEntity.setPhotoPath(photoPath);
                    }

                }
                Field[] fileds = cMarkStakeEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cMarkStakeEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cMarkStakeWebService.save(params, "C_MARK_STAKE");
                return cMarkStakeWebService.save(params, "c_mark_stake");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CMarkStakeEntity> cMarkStakeEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CMarkStakeEntity entity : cMarkStakeEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cMarkStakeDao.save(cMarkStakeEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cMarkStakeWebService.report(cMarkStakeEntities.get(0).getId());
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
//                for (CMarkStakeEntity entity : cMarkStakeEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cMarkStakeDao.save(cMarkStakeEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cMarkStakeWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CMarkStakeEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cMarkStakeDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cMarkStakeDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cMarkStakeWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CMarkStakeEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CMarkStakeEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CMarkStakeEntity> item) {
                for (CMarkStakeEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cMarkStakeDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CMarkStakeEntity> loadFromDb() {
                return cMarkStakeDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CMarkStakeEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CMarkStakeEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CMarkStakeEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CMarkStakeEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CMarkStakeEntity> item) {
                for (CMarkStakeEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cMarkStakeDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CMarkStakeEntity> loadFromDb() {
                return cMarkStakeDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CMarkStakeEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CMarkStakeEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CMarkStakeEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CMarkStakeEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CMarkStakeEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CMarkStakeEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cMarkStakeDao.loadListSum(localStatus);
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
                    List<CMarkStakeEntity> entities = cMarkStakeDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CMarkStakeEntity>> response = new Response<List<CMarkStakeEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CMarkStakeEntity> entities = cMarkStakeDao.loadList(fianlPage, rows, localStatus);
                Response<List<CMarkStakeEntity>> response = new Response<List<CMarkStakeEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CMarkStakeEntity>>>> createCall() {
                return cMarkStakeWebService.list(page, rows, "C_MARK_STAKE", status);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen,CMarkStakeEntity cMarkStakeEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cMarkStakeDao.del(cMarkStakeEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cMarkStakeEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cMarkStakeEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cMarkStakeEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cMarkStakeEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cMarkStakeDao.save(cMarkStakeEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {

                String judge = cMarkStakeEntity.getJudge();

                if ("unqualified".equals(judge)) { //不合格
                    if (TypeStatus.PROJECTMANAGER.equals(owner)) { //业主抽查
                        return cMarkStakeWebService.completeTaskJudge(cMarkStakeEntity.getId(), cMarkStakeEntity.getJudge(), (TypeStatus.PROJECTMANAGER.equals(owner) ? TypeStatus.ENTER : TypeStatus.OWNERS), cMarkStakeEntity.getRemark());
                    }
                    //选择不合格的时候
                    cMarkStakeEntity.setApproveStatus(TypeStatus.ENTER);
                    return cMarkStakeWebService.completeTaskJudge(cMarkStakeEntity.getId(), cMarkStakeEntity.getJudge(), TypeStatus.ENTER, cMarkStakeEntity.getRemark());
                }
                if(isShen){//如果是审核的话
                    return cMarkStakeWebService.completeTaskJudge(cMarkStakeEntity.getId(), cMarkStakeEntity.getJudge(),TypeStatus.OWNERS, cMarkStakeEntity.getRemark());
                }else {//如果是校验的话
                    return cMarkStakeWebService.completeTaskJudge(cMarkStakeEntity.getId(), cMarkStakeEntity.getJudge(), TypeStatus.PROJECTMANAGER, cMarkStakeEntity.getRemark());

                }
            }
        }.getAsLiveData();
    }


    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CMarkStakeEntity> cMarkStakeEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CMarkStakeEntity entity : cMarkStakeEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cMarkStakeDao.save(cMarkStakeEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cMarkStakeWebService.revoked(cMarkStakeEntities.get(0).getId(), "C_MARK_STAKE", (type == 1 ? "enter" : "supervisor"))
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

                return cMarkStakeWebService.getPic(path);
            }
        }.getAsLiveData();

    }

}
