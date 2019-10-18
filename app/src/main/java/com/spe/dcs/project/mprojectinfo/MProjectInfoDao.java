package com.spe.dcs.project.mprojectinfo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:59.1项目
 * Author.
 * Data:
 */
@Dao
public interface MProjectInfoDao {

    @Insert(onConflict = REPLACE)
    long save(MProjectInfoEntity mProjectInfoEntity);

    @Insert(onConflict = REPLACE)
    void save(List<MProjectInfoEntity> mProjectInfoEntities);

    @Query("SELECT * FROM M_PROJECT_INFO")
    List<MProjectInfoEntity> loadList();

}
