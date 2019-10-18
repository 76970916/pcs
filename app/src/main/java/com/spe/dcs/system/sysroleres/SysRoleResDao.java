package com.spe.dcs.system.sysroleres;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:角色资源
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysRoleResDao {
    @Insert(onConflict = REPLACE)
    long save(SysRoleResEntity sysRoleResEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysRoleResEntity> sysRoleResEntities);

    @Delete
    void del(List<SysRoleResEntity> sysRoleResEntities);

    @Query("SELECT * FROM SYS_ROLE_RES ")
    List<SysRoleResEntity> loadList();
}
