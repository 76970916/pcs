package com.spe.dcs.login;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.spe.dcs.system.sysuser.SysUserEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface LoginDao {

    @Insert(onConflict = REPLACE)
    void save(SysUserEntity sysUserEntity);

    @Query("SELECT * FROM sys_user WHERE id = :id")
    LiveData<SysUserEntity> load(String id);

    @Query("SELECT * FROM sys_user WHERE nameen = :name ")
    SysUserEntity login(String name);
}
