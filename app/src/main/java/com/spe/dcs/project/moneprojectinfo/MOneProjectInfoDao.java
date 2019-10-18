package com.spe.dcs.project.moneprojectinfo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:子项目
 * Author.
 * Data:
 */
@Dao
public interface MOneProjectInfoDao {

    @Insert(onConflict = REPLACE)
    long save(MOneProjectInfoEntity mOneProjectInfoEntity);

    @Insert(onConflict = REPLACE)
    void save(List<MOneProjectInfoEntity> mOneProjectInfoEntities);

    @Query("SELECT * FROM M_ONE_PROJECT_INFO")
    List<MOneProjectInfoEntity> loadList();

    @Query("SELECT * FROM M_ONE_PROJECT_INFO WHERE PROJECT_NUM =:projectNumber")
    List<MOneProjectInfoEntity> loadLists(String projectNumber);

}
