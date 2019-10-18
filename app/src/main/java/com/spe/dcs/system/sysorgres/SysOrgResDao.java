package com.spe.dcs.system.sysorgres;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:组织资源
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysOrgResDao {
    @Insert(onConflict = REPLACE)
    long save(SysOrgResEntity sysOrgResEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysOrgResEntity> sysOrgResEntities);

    @Delete
    void del(List<SysOrgResEntity> sysOrgResEntities);

    @Query("SELECT * FROM SYS_ORG_RES ")
    List<SysOrgResEntity> loadList();

}
