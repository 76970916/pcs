package com.spe.dcs.system.sysres;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:资源
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysResDao {
    @Insert(onConflict = REPLACE)
    long save(SysResEntity sysResEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysResEntity> sysResEntities);

    @Delete
    void del(List<SysResEntity> sysResEntities);

    @Query("SELECT * FROM SYS_RES ")
    List<SysResEntity> loadList();

}
