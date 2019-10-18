package com.spe.dcs.system.syslistfield;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:列表字段
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysListFieldDao {

    @Insert(onConflict = REPLACE)
    long save(SysListFieldEntity sysListFieldEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysListFieldEntity> sysListFieldEntities);

    @Delete
    void del(List<SysListFieldEntity> sysListFieldEntities);

    @Query("SELECT * FROM SYS_LIST_FIELD ")
    List<SysListFieldEntity> loadList();
}
