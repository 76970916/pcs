package com.spe.dcs.system.sysuser;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:用户
 * Author.
 * Data:
 */
@Dao
public interface SysUserDao {

    @Insert(onConflict = REPLACE)
    long save(SysUserEntity SysUserEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysUserEntity> SysUserEntities);

    @Delete
    void del(List<SysUserEntity> SysUserEntities);

    @Query("SELECT * FROM SYS_USER ")
    List<SysUserEntity> loadList();

    @Query("SELECT * FROM SYS_USER WHERE USER_CODE = :name ")
    SysUserEntity login(String name);

}
