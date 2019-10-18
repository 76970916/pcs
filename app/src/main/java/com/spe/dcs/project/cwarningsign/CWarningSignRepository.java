package com.spe.dcs.project.cwarningsign;

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
import com.spe.dcs.project.chddcro.CHddCroEntity;

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
 * Desc:30_施工警示牌
 * Author.
 * Data:
 */

public class CWarningSignRepository {

    private final CWarningSignDao cWarningSignDao;
    private final CWarningSignWebService cWarningSignWebService;
    private RequestBody body;

    @Inject
    public CWarningSignRepository(Retrofit retrofit, PcsDatabase pcsDatabase) {
        this.cWarningSignWebService = retrofit.create(CWarningSignWebService.class);
        this.cWarningSignDao = pcsDatabase.cWarningSignDao();
        Log.d("wwww", "CWarningSignRepository被创建了");
    }

    //保存
    public LiveData<Resource<RespEntity>> save(CWarningSignEntity cWarningSignEntity, boolean isNew) {
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
                    if (TypeStatus.APP_OWNER.equals(cWarningSignEntity.getApproveStatus())) {
                        cWarningSignEntity.setStatus(Status.NORMAL);
                        cWarningSignDao.save(cWarningSignEntity);
                    }
//                    已上报的数据上传
                    if (TypeStatus.APP_SUPERVISOR.equals(cWarningSignEntity.getApproveStatus())) {
                        cWarningSignEntity.setStatus(Status.NORMAL);
                        cWarningSignDao.save(cWarningSignEntity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cWarningSignEntity.setStatus(Status.LOCAL);
                return new RespEntity(1, "", cWarningSignDao.save(cWarningSignEntity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                Map<String, RequestBody> params = new HashMap<>();
                String photoPath = cWarningSignEntity.getPhotoPath();
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
                    cWarningSignEntity.setPhotoPath("");
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
                        cWarningSignEntity.setPhotoPath(json);
                    } else {
                        cWarningSignEntity.setPhotoPath(photoPath);
                    }
                }
                Field[] fileds = cWarningSignEntity.getClass().getDeclaredFields();
                for (Field field : fileds) {
                    field.setAccessible(true);
                    try {
                        String value = field.get(cWarningSignEntity).toString();
                        body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) value);
                        params.put(field.getName(), body);
                    } catch (Exception ex) {

                        Log.d("Repository", ex.toString());
                    }

                }

//                return cWarningSignWebService.save(params, "C_WARNING_SIGN");
                return cWarningSignWebService.save(params, "c_warning_sign");
            }
        }.getAsLiveData();

    }

    //    上报（离线接口）
    public LiveData<Resource<FlagRespEntity>> report(List<CWarningSignEntity> cWarningSignEntities) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {

            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CWarningSignEntity entity : cWarningSignEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
//              保存在数据库中
                    cWarningSignDao.save(cWarningSignEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
//                return cWarningSignWebService.report(cWarningSignEntities.get(0).getId());
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
//                for (CWarningSignEntity entity : cWarningSignEntities) {
//                    entity.setStatus(Status.LOCAL_MODIFY);
////              保存在数据库中
//                    cWarningSignDao.save(cWarningSignEntities);
//                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cWarningSignWebService.report(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<RespEntity>> delete(List<CWarningSignEntity> entities) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
//                if (item.getCode() != 1) return;
//                if (entities.size() != 0 && entities != null) {
//                    cWarningSignDao.del(entities.get(0));
//                }
            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                cWarningSignDao.del(entities.get(0));
                return new RespEntity(1, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cWarningSignWebService.delete(entities.get(0).getId());
            }
        }.getAsLiveData();
    }

    //已审核数据的上传
    public LiveData<Resource<List<CWarningSignEntity>>> list4Upload() {
        return new NetworkBoundResource<List<CWarningSignEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWarningSignEntity> item) {
                for (CWarningSignEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_OWNER);
                    entity.setStatus(Status.NORMAL);
                    cWarningSignDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CWarningSignEntity> loadFromDb() {
                return cWarningSignDao.loadLocalList4Upload();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWarningSignEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWarningSignEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }

    //    已上报数据的上传
    public LiveData<Resource<List<CWarningSignEntity>>> list4UploadSu() {
        return new NetworkBoundResource<List<CWarningSignEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<CWarningSignEntity> item) {
                for (CWarningSignEntity entity : item) {
                    entity.setApproveStatus(TypeStatus.APP_SUPERVISOR);
                    entity.setStatus(Status.NORMAL);
                    cWarningSignDao.save(entity);
                }
            }

            @NonNull
            @Override
            protected List<CWarningSignEntity> loadFromDb() {
                return cWarningSignDao.list4UploadSu();
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CWarningSignEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<CWarningSignEntity>>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<Response<List<CWarningSignEntity>>>> list(final int page, final int rows, final String status) {
        return new NetworkBoundResource<Response<List<CWarningSignEntity>>>() {

            @Override
            protected void saveCallResult(@NonNull Response<List<CWarningSignEntity>> item) {
            }

            @NonNull
            @Override
            protected Response<List<CWarningSignEntity>> loadFromDb() {
                String localStatus = status;
                //已上报、待审核列表数据相同
                if (localStatus.equals(TypeStatus.SUPERVISOR)) {
                    localStatus = TypeStatus.SUPERVISOR;
                } else if (localStatus.equals(TypeStatus.OWNERS)) {
                    localStatus = TypeStatus.OWNERS;
                }

                int listSum = cWarningSignDao.loadListSum(localStatus);
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
                    List<CWarningSignEntity> entities = cWarningSignDao.loadList(fianlPage, rows, localStatus);
                    Response<List<CWarningSignEntity>> response = new Response<List<CWarningSignEntity>>();
                    response.setData(entities);
                    return response;
                }
                List<CWarningSignEntity> entities = cWarningSignDao.loadList(fianlPage, rows, localStatus);
                Response<List<CWarningSignEntity>> response = new Response<List<CWarningSignEntity>>();
                response.setData(entities);
                return response;
            }

            @NonNull
            @Override
            protected LiveData<Resource<Response<List<CWarningSignEntity>>>> createCall() {
                return cWarningSignWebService.list(page, rows, "C_WARNING_SIGN", status);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen,CWarningSignEntity cWarningSignEntity, String owner) {
        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
                if (item.isFlag())
                    cWarningSignDao.del(cWarningSignEntity);
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                if (TypeStatus.OWNER.equals(owner)) {
                    return null;
                } else {
                    cWarningSignEntity.setStatus(com.spe.dcs.common.Status.LOCAL_MODIFY);
                    String judge = cWarningSignEntity.getJudge();
                    if ("unqualified".equals(judge)) { //不合格
                        cWarningSignEntity.setApproveStatus(TypeStatus.ENTER);
                    } else if ("qualified".equals(judge)) {//合格
                        cWarningSignEntity.setApproveStatus(TypeStatus.OWNERS);
                    }
                    return new FlagRespEntity(true, "", cWarningSignDao.save(cWarningSignEntity));
                }

            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                String judge = cWarningSignEntity.getJudge();
                if ("unqualified".equals(judge)) { //不合格
                    if (TypeStatus.PROJECTMANAGER.equals(owner)) { //业主抽查
                        return cWarningSignWebService.completeTaskJudge(cWarningSignEntity.getId(), cWarningSignEntity.getJudge(), (TypeStatus.PROJECTMANAGER.equals(owner) ? TypeStatus.ENTER : TypeStatus.OWNERS), cWarningSignEntity.getRemark());
                    }
                    //选择不合格的时候
                    cWarningSignEntity.setApproveStatus(TypeStatus.ENTER);
                    return cWarningSignWebService.completeTaskJudge(cWarningSignEntity.getId(), cWarningSignEntity.getJudge(), TypeStatus.ENTER, cWarningSignEntity.getRemark());
                }
                if(isShen){//如果是审核的话
                    return cWarningSignWebService.completeTaskJudge(cWarningSignEntity.getId(), cWarningSignEntity.getJudge(),TypeStatus.OWNERS, cWarningSignEntity.getRemark());
                }else {//如果是校验的话
                    return cWarningSignWebService.completeTaskJudge(cWarningSignEntity.getId(), cWarningSignEntity.getJudge(), TypeStatus.PROJECTMANAGER, cWarningSignEntity.getRemark());

                }


            }
        }.getAsLiveData();
    }


    //    撤回
    public LiveData<Resource<FlagRespEntity>> revoked(List<CWarningSignEntity> cWarningSignEntities, int type) {

        return new NetworkBoundResource<FlagRespEntity>() {
            @Override
            protected void saveCallResult(@NonNull FlagRespEntity item) {
            }

            @NonNull
            @Override
            protected FlagRespEntity loadFromDb() {
                for (CWarningSignEntity entity : cWarningSignEntities) {
                    entity.setStatus(Status.LOCAL_MODIFY);
                    if (type == 1) {//已上报的撤回
                        entity.setApproveStatus(TypeStatus.ENTER);
                    } else if (type == 2) {  //已审核的撤回
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                    }
//                    保存在数据库中
                    cWarningSignDao.save(cWarningSignEntities);
                }
                return new FlagRespEntity(true, "", "");
            }

            @NonNull
            @Override
            protected LiveData<Resource<FlagRespEntity>> createCall() {
                return cWarningSignWebService.revoked(cWarningSignEntities.get(0).getId(), "C_WARNING_SIGN", (type == 1 ? "enter" : "supervisor"))
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

                return cWarningSignWebService.getPic(path);
            }
        }.getAsLiveData();

    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CWarningSignEntity entity) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity respEntity) {

            }

            @NonNull
            @Override
            protected RespEntity loadFromDb() {
                return new RespEntity(1, "", cWarningSignDao.save(entity));
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return cWarningSignWebService.findUpdateId(entity.getId());
            }
        }.getAsLiveData();

    }


}
