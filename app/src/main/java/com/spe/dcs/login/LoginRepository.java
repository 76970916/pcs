package com.spe.dcs.login;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.system.sysuser.SysUserDao;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.utility.BCrypt;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;


public class LoginRepository {

    private final LoginWebService loginWebservice;
    private final SysUserDao sysUserDao;


    @Inject
    public LoginRepository(Retrofit retrofit, SysDatabase sysDatabase) {
        this.loginWebservice = retrofit.create(LoginWebService.class);
        this.sysUserDao = sysDatabase.sysUserDao();


    }

    public LiveData<Resource<RespEntity>> login(final String username, final String password) {
        return new NetworkBoundResource<RespEntity>() {
            @Override
            protected void saveCallResult(@NonNull RespEntity item) {
                if (item.getCode() != 1) {
                    return;
                } else {
                    LinkedTreeMap map = (LinkedTreeMap) item.getData();
                    GsonBuilder builder = new GsonBuilder();
                    builder.enableComplexMapKeySerialization();
                    Gson gson = builder.create();
                    String json = gson.toJson(map);
                    SysUserEntity entity = gson.fromJson(json, new TypeToken<SysUserEntity>() {
                    }.getType());
//                    Logger.json(json);
                   // loginDao.save(entity);
                    PcsApplication.getInstance().setSysUserEntity(entity);
                }
            }

            @NonNull
            @Override
            @WorkerThread
            protected RespEntity loadFromDb() {
                SysUserEntity entity = sysUserDao.login(username);
                boolean isSame = false;
                if (entity != null) {
                    isSame = BCrypt.checkpw(password, entity.getPassword());
                    if (isSame) PcsApplication.getInstance().setSysUserEntity(entity);
                }
                return new RespEntity(!isSame ? 0 : 1, "用户名或密码错误", entity);
            }

            @NonNull
            @Override
            protected LiveData<Resource<RespEntity>> createCall() {
                return loginWebservice.login(username, password);
            }
        }.getAsLiveData();
    }

}