package com.spe.dcs.system.sysrole;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.db.SysDatabase;
import com.spe.dcs.app.net.NetworkBoundResource;
import com.spe.dcs.system.sysroleuser.SysRoleUserDao;
import com.spe.dcs.system.sysroleuser.SysRoleUserEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Desc:角色
 * Author.
 * Data:${DATA}
 */

public class SysRoleResposity {

    private final SysRoleDao sysRoleDao;
    private final SysRoleUserDao sysRoleUserDao;
    private final SysRoleWebService sysRoleWebService;

    @Inject
    public SysRoleResposity(Retrofit retrofit, SysDatabase sysDatabase) {
        this.sysRoleWebService = retrofit.create(SysRoleWebService.class);
        this.sysRoleDao = sysDatabase.sysRoleDao();
        this.sysRoleUserDao = sysDatabase.sysRoleUserDao();
    }


    public LiveData<Resource<List<SysRoleEntity>>> list(boolean local) {
        return new NetworkBoundResource<List<SysRoleEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysRoleEntity> item) {
                sysRoleDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SysRoleEntity> data) {
                return super.shouldFetch(data) && !local;
            }

            @NonNull
            @Override
            protected List<SysRoleEntity> loadFromDb() {
                return sysRoleDao.loadList();
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysRoleEntity>>> createCall() {
                return sysRoleWebService.list();
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<SysRoleEntity>>> findRoleListByUserId(String id) {
        return new NetworkBoundResource<List<SysRoleEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<SysRoleEntity> item) {
                sysRoleDao.save(item);
            }


            @NonNull
            @Override
            protected List<SysRoleEntity> loadFromDb() {
                List<SysRoleEntity> sysRoleEntities = new ArrayList<>();
                List<SysRoleUserEntity> sysRoleUserEntities = sysRoleUserDao.loadRoleList(id);
                if (sysRoleUserEntities.size() != 0 && sysRoleUserEntities != null) {
                    for (int i = 0; i < sysRoleUserEntities.size(); i++) {
                        String roleId = sysRoleUserEntities.get(i).getRoleId();
                        sysRoleEntities.addAll(sysRoleDao.loadRole(roleId));
                    }
                }
                return sysRoleEntities;
            }

            @NonNull
            @Override
            protected LiveData<Resource<List<SysRoleEntity>>> createCall() {
                return sysRoleWebService.findRoleListByUserId(id);
            }
        }.getAsLiveData();
    }


    public LiveData<Resource<SysRoleEntity>> findDataByCode(String code) {
        return new NetworkBoundResource<SysRoleEntity>() {
            @Override
            protected void saveCallResult(@NonNull SysRoleEntity item) {
            }

            @NonNull
            @Override
            protected SysRoleEntity loadFromDb() {
//                return sysRoleDao.findDataByCode(code);
                return null;
            }

            @NonNull
            @Override
            protected LiveData<Resource<SysRoleEntity>> createCall() {
                return sysRoleWebService.findDataByCode(code);
            }
        }.getAsLiveData();
    }


}
