package com.spe.dcs.system.sysroleuser;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:角色用户
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysRoleUserDao {
    @Insert(onConflict = REPLACE)
    long save(SysRoleUserEntity sysRoleUserEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysRoleUserEntity> sysRoleUserEntities);

    @Delete
    void del(List<SysRoleUserEntity> sysRoleUserEntities);

    @Query("SELECT * FROM SYS_ROLE_USER ")
    List<SysRoleUserEntity> loadList();

    @Query("SELECT * FROM SYS_ROLE_USER WHERE USER_ID= :id")
    List<SysRoleUserEntity> loadRoleList(String id);
}
