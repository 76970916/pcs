package com.spe.dcs.system.sysuserres;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysUserResDao {
    @Insert(onConflict = REPLACE)
    long save(SysUserResEntity sysUserResEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysUserResEntity> sysUserResEntities);

    @Delete
    void del(List<SysUserResEntity> sysUserResEntities);

    @Query("SELECT * FROM SYS_USER_RES ")
    List<SysUserResEntity> loadList();
}
