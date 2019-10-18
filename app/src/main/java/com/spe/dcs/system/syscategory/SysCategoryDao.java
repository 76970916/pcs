package com.spe.dcs.system.syscategory;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:分类表
 * Author.
 * Data:
 */
@Dao
public interface SysCategoryDao {


    @Insert(onConflict = REPLACE)
    void save(List<SysCategoryEntity> SysCategoryEntities);

    @Delete
    void del(List<SysCategoryEntity> SysCategoryEntities);

    @Query("SELECT * FROM SYS_CATEGORY ")
    List<SysCategoryEntity> loadList();

}
