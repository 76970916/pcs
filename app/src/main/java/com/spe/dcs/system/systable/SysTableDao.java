package com.spe.dcs.system.systable;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:表
 * Author.
 * Data:
 */
@Dao
public interface SysTableDao {

    @Insert(onConflict = REPLACE)
    long save(SysTableEntity SysTableEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysTableEntity> SysTableEntities);

    @Delete
    void del(List<SysTableEntity> SysTableEntities);

    @Query("SELECT * FROM SYS_TABLE ")
    List<SysTableEntity> loadList();

}
