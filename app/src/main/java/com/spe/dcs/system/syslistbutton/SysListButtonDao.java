package com.spe.dcs.system.syslistbutton;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:列表按钮
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysListButtonDao {
    @Insert(onConflict = REPLACE)
    long save(SysListButtonEntity sysListButtonEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysListButtonEntity> sysListButtonEntities);

    @Delete
    void del(List<SysListButtonEntity> sysListButtonEntities);

    @Query("SELECT * FROM SYS_LIST_BUTTON ")
    List<SysListButtonEntity> loadList();
}
