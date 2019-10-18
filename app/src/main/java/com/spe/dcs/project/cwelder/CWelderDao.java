package com.spe.dcs.project.cwelder;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:施工焊工数据
 * Author.
 * Data:${DATA}
 */
@Dao
public interface CWelderDao {
    @Insert(onConflict = REPLACE)
    long save(CWelderEntity cWelderEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CWelderEntity> cWelderEntities);

    @Delete
    void del(List<CWelderEntity> cWelderEntities);

    @Query("SELECT * FROM C_WELDER ")
    List<CWelderEntity> loadList();

}
