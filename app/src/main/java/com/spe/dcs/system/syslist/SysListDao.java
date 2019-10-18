package com.spe.dcs.system.syslist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:列表
 * Author.
 * Data:
 */
@Dao
public interface SysListDao {

    @Insert(onConflict = REPLACE)
    long save(SysListEntity SysListEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysListEntity> SysListEntities);

    @Delete
    void del(List<SysListEntity> SysListEntities);

    @Query("SELECT * FROM SYS_LIST  ")
    List<SysListEntity> loadList();

}
